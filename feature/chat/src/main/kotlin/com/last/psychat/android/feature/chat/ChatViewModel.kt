package com.last.psychat.android.feature.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.usecase.chat.GetSessionIdUseCase
import com.last.psychat.android.core.domain.usecase.chat.SendChatMessageUseCase
import com.last.psychat.android.core.ui.UiText
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
import timber.log.Timber

data class ChatUiState(
  val chatMessages: List<ChatMessage> = listOf(),
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
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val sessionId: Long =
    requireNotNull(savedStateHandle.get<Long>(CHAT_SESSION_ID)) { "CHAT_SESSION_ID is require." }

  private val _uiState = MutableStateFlow(ChatUiState())
  val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<ChatUiEvent>()
  val eventFlow: SharedFlow<ChatUiEvent> = _eventFlow.asSharedFlow()

  init {
    viewModelScope.launch {
      // val sessionId = getSessionIdUseCase()
      val result = sendChatMessageUseCase(
        ChatRequestEntity(
          sessionId = sessionId,
          messageContent = "너 죽을래?",
        )
      )
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val botMessage = result.getOrNull()!!.responseContent
          Timber.d(botMessage)
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ChatUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
  }

  fun updateChatInputMessage(message: String) {
    _uiState.update {
      it.copy(chatInputMessage = message)
    }
  }
}
