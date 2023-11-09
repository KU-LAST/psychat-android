package com.last.psychat.android.core.domain.entity.chat

data class PreviousChatDetailEntity(
  val userMessages: List<UserMessageEntity>,
  val botMessages: List<BotMessageEntity>,
)

data class UserMessageEntity(
  val messageContent: String,
  val timestamp: String,
)

data class BotMessageEntity(
  val responseContent: String,
  val timestamp: String,
)
