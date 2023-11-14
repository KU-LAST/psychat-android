package com.last.psychat.android.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.core.domain.usecase.chat.GetPreviousChatListUseCase
import com.last.psychat.android.core.domain.usecase.chat.StartChatSessionUseCase
import com.last.psychat.android.core.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
  val sessionId: Long = -1,
  val previousChatList: ImmutableList<PreviousChatEntity> = persistentListOf(),
  val isEndChat: Boolean = false,
  val isNetworkError: Boolean = false,
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed interface MainUiEvent {
  data class NavigateToChat(val sessionId: Long, val isEndChat: Boolean) : MainUiEvent
  data class ShowToast(val message: UiText) : MainUiEvent
}

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getPreviousChatListUseCase: GetPreviousChatListUseCase,
  private val startChatSessionUseCase: StartChatSessionUseCase,
) : ViewModel() {

  private val _uiState = MutableStateFlow(MainUiState())
  val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<MainUiEvent>()
  val eventFlow: SharedFlow<MainUiEvent> = _eventFlow.asSharedFlow()

  fun getPreviousChatList() {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
        )
      }
      val result = getPreviousChatListUseCase()
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val previousChatList = result.getOrNull()!!.previousChatList
          _uiState.update {
            it.copy(previousChatList = previousChatList.toImmutableList())
          }
        }
        result.isFailure -> {
          _uiState.update {
            it.copy(
              isNetworkError = true,
            )
          }
        }
      }
      _uiState.update {
        it.copy(
          isLoading = false,
        )
      }
    }
  }

  fun startChatSession() {
    if (uiState.value.isLoading) return

    _uiState.update { it.copy(isLoading = true) }
    viewModelScope.launch {
      val result = startChatSessionUseCase()
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val sessionId = result.getOrNull()!!.sessionId
          // setSessionIdUseCase(sessionId)
          _uiState.update {
            it.copy(
              sessionId = sessionId,
            )
          }
          _eventFlow.emit(
            MainUiEvent.NavigateToChat(
              sessionId = sessionId,
              isEndChat = false,
            ),
          )
        }

        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(MainUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
    _uiState.update {
      it.copy(
        isLoading = false,
      )
    }

//    viewModelScope.launch {
//      _eventFlow.emit(MainUiEvent.NavigateToChat)
//    }
  }

  fun resumeChatSession(sessionId: Long) {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          sessionId = sessionId,
        )
      }
      _eventFlow.emit(
        MainUiEvent.NavigateToChat(
          sessionId = sessionId,
          isEndChat = true,
        ),
      )
    }
  }

  fun closeNetworkErrorDialog() {
    _uiState.update {
      it.copy(
        isNetworkError = false,
      )
    }
  }
}
