package com.last.psychat.android.feature.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.usecase.chat.CheckEmotionIsJudgedUseCase
import com.last.psychat.android.core.domain.usecase.chat.EndChatSessionUseCase
import com.last.psychat.android.core.domain.usecase.chat.GetPreviousChatDetailUseCase
import com.last.psychat.android.core.domain.usecase.chat.SendChatMessageUseCase
import com.last.psychat.android.core.domain.util.EndChatSessionResponseServerError
import com.last.psychat.android.core.ui.UiText
import com.last.psychat.android.feature.chat.mapper.toUiModel
import com.last.psychat.android.feature.chat.model.ChatMessageUiModel
import com.last.psychat.android.feature.chat.navigation.IS_END_CHAT
import com.last.psychat.android.feature.chat.navigation.SESSION_ID
import com.last.psychat.core.util.getCurrentTimeFormatted
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ChatUiState(
  val chatMessageList: List<ChatMessageUiModel>? = null,
  // val chatMessageList: List<ChatMessageUiModel> = emptyList(),
  val chatInputMessage: String = "",
  val isEndChat: Boolean = false,
  val emotion: String = "",
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed interface ChatUiEvent {
  data class NavigateToResult(val emotion: String) : ChatUiEvent
  data class ShowToast(val message: UiText) : ChatUiEvent
}

@HiltViewModel
class ChatViewModel @Inject constructor(
  private val sendChatMessageUseCase: SendChatMessageUseCase,
  private val getPreviousChatDetailUseCase: GetPreviousChatDetailUseCase,
  private val checkEmotionIsJudgedUseCase: CheckEmotionIsJudgedUseCase,
  private val endChatSessionUseCase: EndChatSessionUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val sessionId: Long =
    requireNotNull(savedStateHandle.get<Long>(SESSION_ID)) { "sessionId is required." }

  private val isEndChat: Boolean =
    requireNotNull(savedStateHandle.get<Boolean>(IS_END_CHAT)) { "isEndChat is required." }

//  private val sessionId: Long = 0L
//  private val isEndChat: Boolean = false

  private val _uiState = MutableStateFlow(ChatUiState())
  val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<ChatUiEvent>()
  val eventFlow: SharedFlow<ChatUiEvent> = _eventFlow.asSharedFlow()

//  init {
//    viewModelScope.launch {
//      _uiState.update {
//        it.copy(
//          chatMessageList = chatMessageList,
//          isEndChat = isEndChat
//        )
//      }
//    }
//  }

  init {
    _uiState.update {
      it.copy(
        isEndChat = isEndChat,
      )
    }
    getPreviousChatDetail(sessionId)
  }

  private fun getPreviousChatDetail(sessionId: Long) {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
        )
      }
      val result = getPreviousChatDetailUseCase(sessionId)
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val previousChatMessageList = result.getOrNull()!!
          _uiState.update {
            it.copy(
              chatMessageList = previousChatMessageList.map { prevChatMsg -> prevChatMsg.toUiModel() },
            )
          }
        }

        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
      _uiState.update {
        it.copy(
          isLoading = false
        )
      }
    }
  }

  fun sendChatMessage() {
    if (uiState.value.isLoading) return

    _uiState.update { it.copy(isLoading = true) }
    viewModelScope.launch {
      val messageContent = ChatMessageUiModel(
        message = _uiState.value.chatInputMessage,
        timestamp = getCurrentTimeFormatted(),
        isUser = true,
      )
      _uiState.update {
        it.copy(
          chatMessageList = it.chatMessageList?.plus(messageContent),
          // chatMessageList = it.chatMessageList + messageContent
        )
      }
      val result = sendChatMessageUseCase(
        ChatRequestEntity(
          sessionId = sessionId,
          messageContent = uiState.value.chatInputMessage,
        ),
      )
      _uiState.update { it.copy(chatInputMessage = "") }
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val responseMessage = result.getOrNull()!!.toUiModel()
          _uiState.update {
            it.copy(
              chatMessageList = it.chatMessageList?.plus(responseMessage),
              // chatMessageList = chatMessageList + responseMessage
            )
          }
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
      _uiState.update { it.copy(isLoading = false) }
    }
  }

  fun updateChatInputMessage(message: String) {
    _uiState.update {
      it.copy(chatInputMessage = message)
    }
  }

  fun checkEmotionIsJudged() {
    viewModelScope.launch {
      val result = checkEmotionIsJudgedUseCase(EndChatEntity(sessionId = sessionId))
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val isJudged = result.getOrNull()!!.isJudged
          if (isJudged) {
            endChatSession()
          } else {
            _eventFlow.emit(ChatUiEvent.ShowToast(UiText.StringResource(R.string.emotion_judgement_fail)))
          }
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
  }

  private fun endChatSession() {
    viewModelScope.launch {
      val result = endChatSessionUseCase(EndChatEntity(sessionId = sessionId))
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val emotion = result.getOrNull()!!.emotion
          _uiState.update {
            it.copy(
              emotion = emotion,
            )
          }
          _eventFlow.emit(
            ChatUiEvent.NavigateToResult(
              emotion = withContext(Dispatchers.IO) {
                URLEncoder.encode(
                  emotion,
                  StandardCharsets.UTF_8.toString(),
                )
              },
            ),
          )
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          if (exception.cause == EndChatSessionResponseServerError) {
            _eventFlow.emit(ChatUiEvent.ShowToast(UiText.StringResource(R.string.already_end_chat_session)))
          } else {
            _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
          }
        }
      }
    }
  }
}
