package com.last.psychat.android.core.domain.entity.chat

data class PreviousChatDetailEntity(
  val userMessages: List<UserMessageEntity>,
  val botMessages: List<BotMessageEntity>,
)

interface MessageEntity {
  val timestamp: String
  val messageContent: String
}

data class UserMessageEntity(
  override val timestamp: String,
  override val messageContent: String,
) : MessageEntity

data class BotMessageEntity(
  override val timestamp: String,
  override val messageContent: String,
) : MessageEntity
