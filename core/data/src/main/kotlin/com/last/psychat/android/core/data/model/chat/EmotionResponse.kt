package com.last.psychat.android.core.data.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmotionResponse(
  @SerialName("emotion")
  val emotion: String,
)
