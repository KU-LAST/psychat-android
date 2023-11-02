package com.last.psychat.android.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.usecase.chat.SetSessionIdUseCase
import com.last.psychat.android.core.domain.usecase.chat.StartChatSessionUseCase
import com.last.psychat.android.core.domain.usecase.login.CreateLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.GetLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.SetLoginTokenUseCase
import com.last.psychat.android.core.ui.UiText
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

data class MainUiState(
  val isSessionIdCreated: Boolean = false,
  val isLoggedIn: Boolean = false,
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed class MainUiEvent {
  data class ShowToast(val message: UiText) : MainUiEvent()
}

@HiltViewModel
class MainViewModel @Inject constructor(
  private val createLoginTokenUseCase: CreateLoginTokenUseCase,
  private val getLoginTokenUseCase: GetLoginTokenUseCase,
  private val setLoginTokenUseCase: SetLoginTokenUseCase,
  private val startChatSessionUseCase: StartChatSessionUseCase,
  private val setSessionIdUseCase: SetSessionIdUseCase,
) : ViewModel() {

  private val _uiState = MutableStateFlow(MainUiState())
  val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<MainUiEvent>()
  val eventFlow: SharedFlow<MainUiEvent> = _eventFlow.asSharedFlow()

  init {
    checkLoginToken()
  }

  private fun checkLoginToken() {
    viewModelScope.launch {
      if (getLoginTokenUseCase().isEmpty()) {
        val result = createLoginTokenUseCase()
        when {
          result.isSuccess && result.getOrNull() != null -> {
            val loginToken = result.getOrNull()!!.token
            setLoginTokenUseCase(loginToken)
          }
          result.isFailure -> {
            val exception = result.exceptionOrNull()!!
            _eventFlow.emit(MainUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
          }
        }
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
          setSessionIdUseCase(sessionId)
          _uiState.update {
            it.copy(isSessionIdCreated = true)
          }
        }

        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(MainUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
    _uiState.update { it.copy(isLoading = false) }
  }
}
