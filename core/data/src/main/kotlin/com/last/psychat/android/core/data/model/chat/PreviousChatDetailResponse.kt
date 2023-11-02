package com.last.psychat.android.core.data.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviousChatDetailResponse(
  @SerialName("chattingList")
  val chattingList: ChattingList,
)

@Serializable
data class ChattingList(
  @SerialName("userMessage")
  val userMessages: List<UserMessage>,

  @SerialName("botMessage")
  val botMessages: List<BotMessage>,
)

@Serializable
data class UserMessage(
  @SerialName("messageContent")
  val messageContent: String,

  @SerialName("timestamp")
  val timestamp: String,
)

@Serializable
data class BotMessage(
  @SerialName("responseContent")
  val responseContent: String,

  @SerialName("timestamp")
  val timestamp: String,
)
