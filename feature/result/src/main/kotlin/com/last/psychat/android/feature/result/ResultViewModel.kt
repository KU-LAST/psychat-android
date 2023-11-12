package com.last.psychat.android.feature.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.last.psychat.android.core.domain.entity.recommend.EmotionRequestEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentEntity
import com.last.psychat.android.core.domain.usecase.recommend.GetRecommendedContentListUseCase
import com.last.psychat.android.core.ui.UiText
import com.last.psychat.android.feature.result.navigation.EMOTION
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

data class ResultUiState(
  val emotion: String = "",
  val recommendedContentList: ImmutableList<RecommendedContentEntity> = persistentListOf(),
  val isLoading: Boolean = false,
  val error: Throwable? = null,
)

sealed interface ResultUiEvent {
  data object NavigateToMain : ResultUiEvent

  data class NavigateToYoutube(val videoUrl: String) : ResultUiEvent
  data class ShowToast(val message: UiText) : ResultUiEvent
}

@HiltViewModel
class ResultViewModel @Inject constructor(
  private val getRecommendedContentListUseCase: GetRecommendedContentListUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {
  private val emotion: String =
    requireNotNull(savedStateHandle.get<String>(EMOTION)) { "emotion is required." }

  private val _uiState = MutableStateFlow(ResultUiState())
  val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

  private val _eventFlow = MutableSharedFlow<ResultUiEvent>()
  val eventFlow: SharedFlow<ResultUiEvent> = _eventFlow.asSharedFlow()

  init {
    _uiState.update {
      it.copy(
        emotion = emotion,
      )
    }
    getRecommendedContentList()
  }

  private fun getRecommendedContentList() {
    viewModelScope.launch {
      val result = getRecommendedContentListUseCase(EmotionRequestEntity(emotion = emotion))
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val recommendedContentList = result.getOrNull()!!.contentsList
          _uiState.update {
            it.copy(
              recommendedContentList = recommendedContentList.toImmutableList()
            )
          }
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()!!
          _eventFlow.emit(ResultUiEvent.ShowToast(UiText.DirectString(exception.message.toString())))
        }
      }
    }
  }
  fun navigateToYoutube(videoUrl: String) {
    viewModelScope.launch {
      _eventFlow.emit(ResultUiEvent.NavigateToYoutube(videoUrl))
    }
  }

  fun navigateToMain() {
    viewModelScope.launch {
      _eventFlow.emit(ResultUiEvent.NavigateToMain)
    }
  }
}

