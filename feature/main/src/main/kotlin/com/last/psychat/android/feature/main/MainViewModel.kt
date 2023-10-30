package com.last.psychat.android.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.usecase.login.CreateLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.GetLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.SetLoginTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
  val isLoggedIn: Boolean = false,
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

@HiltViewModel
class MainViewModel @Inject constructor(
  private val createLoginTokenUseCase: CreateLoginTokenUseCase,
  private val getLoginTokenUseCase: GetLoginTokenUseCase,
  private val setLoginTokenUseCase: SetLoginTokenUseCase,
) : ViewModel() {

  private val _uiState = MutableStateFlow(MainUiState())
  val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      createLoginTokenUseCase()
    }
  }
}
