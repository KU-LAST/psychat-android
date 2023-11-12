package com.last.psychat.android.core.data.model.recommend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmotionRequest(
  @SerialName("emotion")
  val emotion: String,
)
