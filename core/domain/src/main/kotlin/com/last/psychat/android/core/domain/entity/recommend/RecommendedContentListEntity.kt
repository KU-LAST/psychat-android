package com.last.psychat.android.core.domain.entity.recommend

data class RecommendedContentListEntity(
  val contentsList: List<RecommendedContentEntity>,
)

data class RecommendedContentEntity(
  val data: String,
  val title: String,
  val thumbnailUrl: String,
  val videoUrl: String,
)
