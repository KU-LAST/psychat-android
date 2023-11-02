package com.last.psychat.android.core.domain.entity.chat

data class ChatRequestEntity(
  val sessionId: Long,
  val messageContent: String,
)
