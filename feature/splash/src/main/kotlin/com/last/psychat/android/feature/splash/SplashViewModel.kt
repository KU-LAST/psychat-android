package com.last.psychat.android.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SplashUiState(
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed interface SplashUiEvent {
  data object NavigateToMain : SplashUiEvent
  data class ShowToast(val message: UiText) : SplashUiEvent
}

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
  private val _uiState = MutableStateFlow(SplashUiState())
  val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<SplashUiEvent>()
  val eventFlow: SharedFlow<SplashUiEvent> = _eventFlow.asSharedFlow()

  fun navigateToMain() {
    viewModelScope.launch {
      _eventFlow.emit(SplashUiEvent.NavigateToMain)
    }
  }
}