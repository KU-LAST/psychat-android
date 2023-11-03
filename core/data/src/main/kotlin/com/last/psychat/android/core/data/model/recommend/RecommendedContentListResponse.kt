package com.last.psychat.android.core.data.model.recommend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedContentListResponse(
  @SerialName("contentsList")
  val contentsList: List<RecommendedContent>,
)

@Serializable
data class RecommendedContent(
  @SerialName("date")
  val date: String,

  @SerialName("title")
  val title: String,

  @SerialName("thumbnailUrl")
  val thumbnailUrl: String,

  @SerialName("videoUrl")
  val videoUrl: String,
)
