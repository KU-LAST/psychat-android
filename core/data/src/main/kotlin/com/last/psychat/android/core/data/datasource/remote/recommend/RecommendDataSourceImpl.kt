package com.last.psychat.android.core.data.datasource.remote.recommend

import com.last.psychat.android.core.data.model.recommend.EmotionRequest
import com.last.psychat.android.core.data.model.recommend.RecommendedContentListResponse
import com.last.psychat.android.core.data.service.RecommendService
import com.last.psychat.android.core.data.util.extension.safeRequest
import javax.inject.Inject

internal class RecommendDataSourceImpl @Inject constructor(
  private val service: RecommendService,
) : RecommendDataSource {

  override suspend fun getRecommendedContentList(emotionRequest: EmotionRequest): RecommendedContentListResponse? {
    return safeRequest {
      service.getRecommendContents(emotionRequest)
    }
  }
}
