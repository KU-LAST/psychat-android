package com.last.psychat.android.core.data.service

import com.last.psychat.android.core.data.model.recommend.EmotionRequest
import com.last.psychat.android.core.data.model.recommend.RecommendedContentListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RecommendService {
  @POST("recommended-contents")
  suspend fun getRecommendContents(
    @Body emotionRequest: EmotionRequest,
  ): Response<RecommendedContentListResponse>
}
