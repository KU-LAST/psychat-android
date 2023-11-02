package com.last.psychat.android.core.domain.repository

import com.last.psychat.android.core.domain.entity.recommend.EmotionRequestEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentListEntity

interface RecommendRepository {
  suspend fun getRecommendedContentList(emotionRequestEntity: EmotionRequestEntity): RecommendedContentListEntity?
}
