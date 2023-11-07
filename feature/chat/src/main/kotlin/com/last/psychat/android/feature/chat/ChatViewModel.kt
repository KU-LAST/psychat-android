package com.last.psychat.android.feature.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.usecase.chat.EndChatSessionUseCase
import com.last.psychat.android.core.domain.usecase.chat.GetSessionIdUseCase
import com.last.psychat.android.core.domain.usecase.chat.SendChatMessageUseCase
import com.last.psychat.android.core.ui.UiText
import com.last.psychat.android.feature.chat.mapper.toUiModel
import com.last.psychat.android.feature.chat.model.ChatMessage
import com.last.psychat.android.feature.chat.navigation.CHAT_SESSION_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import timber.log.Timber

data class ChatUiState(
  // 반드시 null 로 초기값을 설정 해야 하는지 고민
  val chatMessageList: List<ChatMessage>? = null,
  val chatInputMessage: String = "",
  val isSessionEnd: Boolean = false,
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed class ChatUiEvent {
  data class ShowToast(val message: UiText) : ChatUiEvent()
}

@HiltViewModel
class ChatViewModel @Inject constructor(
  private val sendChatMessageUseCase: SendChatMessageUseCase,
  private val getSessionIdUseCase: GetSessionIdUseCase,
  private val endChatSessionUseCase: EndChatSessionUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val sessionId: Long =
    requireNotNull(savedStateHandle.get<Long>(CHAT_SESSION_ID)) { "sessionId is required." }

  private val _uiState = MutableStateFlow(ChatUiState())
  val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<ChatUiEvent>()
  val eventFlow: SharedFlow<ChatUiEvent> = _eventFlow.asSharedFlow()

  init {
    viewModelScope.launch {
      _uiState.update {
        it.copy(chatMessageList = listOf(
          ChatMessage(
            message = "나 요즘 너무 힘들어",
            timestamp = "오전 10시 12분",
            isUser = true,
          ),
          ChatMessage(
            message = "네 그렇군요, 듣고 있으니 계속 말씀하세요",
            timestamp = "오전 10시 13분",
            isUser = false,
          ),
          ChatMessage(
            message = "학교 팀플에 취업 준비에 너무 할게 많아서 스트레스 받아. 그냥 좀 쉬고 싶어",
            timestamp = "오전 10시 14분",
            isUser = true,
          ),
          ChatMessage(
            message = "그런 일이 있으셨군요. 정말 힘드시겠어요. 제가 옆에서 힘이 되어 드릴께요",
            timestamp = "오전 10시 15분",
            isUser = false,
          ),
          ChatMessage(
            message = "어떻게 스트레스를 그나마 조금이라도 해소할 수 있을까?",
            timestamp = "오전 10시 16분",
            isUser = true,
          ),
          ChatMessage(
            message = "쉬는 시간에 가볍게 산책을 다녀오시는 건 어떨까요?",
            timestamp = "오전 10시 17분",
            isUser = false,
          ),
        ))
      }
    }
  }

  fun sendChatMessage() {
    if (uiState.value.isLoading) return

    _uiState.update { it.copy(isLoading = true) }
    viewModelScope.launch {
      // val sessionId = getSessionIdUseCase()
      val messageContent = ChatMessage(
        message = _uiState.value.chatInputMessage,
        timestamp = Clock.System.now().toString(),
        isUser = true
      )
      _uiState.update {
        it.copy(
          chatMessageList = it.chatMessageList?.plus(messageContent),
          // chatInputMessage = ""
        )
      }
      val result = sendChatMessageUseCase(
        ChatRequestEntity(
          sessionId = sessionId,
          messageContent = uiState.value.chatInputMessage,
        )
      )
      _uiState.update {
        it.copy(
          chatInputMessage = ""
        )
      }
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val responseMessage = result.getOrNull()!!.toUiModel()
          _uiState.update {
            it.copy(
              chatMessageList = it.chatMessageList?.plus(responseMessage),
            )
          }
          // endChatSession()
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

  fun endChatSession() {
    viewModelScope.launch {
      val result = endChatSessionUseCase(EndChatEntity(sessionId = sessionId))
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val emotion = result.getOrNull()
          Timber.d("$emotion")
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
  }
}
