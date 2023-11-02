package com.last.psychat.android.core.data.model.recommend

import kotlinx.serialization.SerialName

data class EmotionRequest(
  @SerialName("emotion")
  val emotion: String,
)

