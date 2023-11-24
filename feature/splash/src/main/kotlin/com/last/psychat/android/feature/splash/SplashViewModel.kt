package com.last.psychat.android.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.usecase.login.CreateLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.GetLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.SetLoginTokenUseCase
import com.last.psychat.android.core.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SplashUiState(
  val isLoggedIn: Boolean = false,
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed interface SplashUiEvent {
  data object NavigateToMain : SplashUiEvent
  data class ShowToast(val message: UiText) : SplashUiEvent
}

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val createLoginTokenUseCase: CreateLoginTokenUseCase,
  private val getLoginTokenUseCase: GetLoginTokenUseCase,
  private val setLoginTokenUseCase: SetLoginTokenUseCase,
) : ViewModel() {

  private val _uiState = MutableStateFlow(SplashUiState())
  val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<SplashUiEvent>()
  val eventFlow: SharedFlow<SplashUiEvent> = _eventFlow.asSharedFlow()

  init {
    viewModelScope.launch {
      delay(500)
      checkLoginToken()
    }
  }

  private fun checkLoginToken() {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          isLoading = true,
        )
      }
      if (getLoginTokenUseCase().isEmpty()) {
        val result = createLoginTokenUseCase()
        when {
          result.isSuccess && result.getOrNull() != null -> {
            val loginToken = result.getOrNull()!!.token
            setLoginTokenUseCase(loginToken)
            _uiState.update {
              it.copy(
                isLoggedIn = true,
              )
            }
            _eventFlow.emit(SplashUiEvent.NavigateToMain)
          }
          result.isFailure -> {
            val exception = result.exceptionOrNull()!!
            _eventFlow.emit(SplashUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
          }
        }
      } else {
        _eventFlow.emit(SplashUiEvent.NavigateToMain)
      }
      _uiState.update {
        it.copy(
          isLoading = false,
        )
      }
    }
  }

  fun navigateToMain() {
    viewModelScope.launch {
      _eventFlow.emit(SplashUiEvent.NavigateToMain)
    }
  }
}
