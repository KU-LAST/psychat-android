package com.last.psychat.android.core.data.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviousChatListResponse(
  @SerialName("previousChatList")
  val previousChatList: List<PreviousChat>,
)

@Serializable
data class PreviousChat(
  @SerialName("sessionId")
  val sessionId: Long,

  @SerialName("startDate")
  val startDate: String,

  @SerialName("emotion")
  val emotion: String,
)

