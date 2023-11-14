package com.last.psychat.android.core.domain.entity.recommend

import androidx.compose.runtime.Stable

data class RecommendedContentListEntity(
  val contentsList: List<RecommendedContentEntity>,
)

@Stable
data class RecommendedContentEntity(
  val date: String,
  val title: String,
  val thumbnailUrl: String,
  val videoUrl: String,
)
