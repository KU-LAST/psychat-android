package com.last.psychat.android.feature.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.last.psychat.android.core.domain.usecase.recommend.GetRecommendedContentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
  private val getRecommendedContentListUseCase: GetRecommendedContentListUseCase,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

}
