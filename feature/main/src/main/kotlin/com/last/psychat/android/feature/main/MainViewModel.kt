package com.last.psychat.android.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.core.domain.usecase.chat.GetPreviousChatListUseCase
import com.last.psychat.android.core.domain.usecase.chat.SetSessionIdUseCase
import com.last.psychat.android.core.domain.usecase.chat.StartChatSessionUseCase
import com.last.psychat.android.core.domain.usecase.login.CreateLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.GetLoginTokenUseCase
import com.last.psychat.android.core.domain.usecase.login.SetLoginTokenUseCase
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
  private val getPreviousChatListUseCase: GetPreviousChatListUseCase,
  private val startChatSessionUseCase: StartChatSessionUseCase,
  private val setSessionIdUseCase: SetSessionIdUseCase,
) : ViewModel() {

  private val _uiState = MutableStateFlow(MainUiState())
  val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<MainUiEvent>()
  val eventFlow: SharedFlow<MainUiEvent> = _eventFlow.asSharedFlow()

  val previousChatList = listOf(
    PreviousChatEntity(
      sessionId = 10,
      startDate = "2023년 11월 5일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 9,
      startDate = "2023년 11월 4일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 8,
      startDate = "2023년 11월 3일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 7,
      startDate = "2023년 11월 2일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 6,
      startDate = "2023년 11월 1일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 5,
      startDate = "2023년 10월 31일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 4,
      startDate = "2023년 10월 30일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 3,
      startDate = "2023년 10월 29일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 2,
      startDate = "2023년 10월 28일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 1,
      startDate = "2023년 10월 27일",
      emotion = "우울"
    ),
  )

  init {
    checkLoginToken()
  }

  fun getPreviousChatList() {
    viewModelScope.launch {
      val result = getPreviousChatListUseCase()
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val previousChatList = result.getOrNull()!!.previousChatList
          _uiState.update {
            it.copy(
              previousChatList = previousChatList.toImmutableList()
            )
          }
        }

        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(MainUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
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
          // setSessionIdUseCase(sessionId)
          _uiState.update {
            it.copy(
              sessionId = sessionId,
              isSessionIdCreated = true,
            )
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
