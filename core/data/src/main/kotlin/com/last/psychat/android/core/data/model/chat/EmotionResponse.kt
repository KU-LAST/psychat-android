package com.last.psychat.android.core.data.model.chat

import kotlinx.serialization.SerialName

data class EmotionResponse(
  @SerialName("emotion")
  val emotion: String,
)
