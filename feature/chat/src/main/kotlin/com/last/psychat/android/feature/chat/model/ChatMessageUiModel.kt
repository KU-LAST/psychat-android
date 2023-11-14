package com.last.psychat.android.feature.chat.model

data class ChatMessageUiModel(
  val message: String,
  val timestamp: String,
  val isUser: Boolean,
)
