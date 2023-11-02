package com.last.psychat.android.core.data.datasource.remote.recommend

import com.last.psychat.android.core.data.model.recommend.EmotionRequest
import com.last.psychat.android.core.data.model.recommend.RecommendedContentListResponse

interface RecommendDataSource {
  suspend fun getRecommendedContentList(emotionRequest: EmotionRequest): RecommendedContentListResponse?
}
