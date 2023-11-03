package com.last.psychat.android.core.data.mapper.recommend

import com.last.psychat.android.core.data.model.recommend.EmotionRequest
import com.last.psychat.android.core.data.model.recommend.RecommendedContent
import com.last.psychat.android.core.data.model.recommend.RecommendedContentListResponse
import com.last.psychat.android.core.domain.entity.recommend.EmotionRequestEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentListEntity

internal fun EmotionRequestEntity.toModel() =
  EmotionRequest(
    emotion = emotion,
  )

internal fun RecommendedContentListResponse.toEntity() =
  RecommendedContentListEntity(
    contentsList = contentsList.map { it.toEntity() },
  )

internal fun RecommendedContent.toEntity() =
  RecommendedContentEntity(
    date = date,
    title = title,
    thumbnailUrl = thumbnailUrl,
    videoUrl = videoUrl,
  )
