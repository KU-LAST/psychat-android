package com.last.psychat.android.core.domain.entity.chat

data class PreviousChatListEntity(
  val previousChatList: List<PreviousChatEntity>,
)

data class PreviousChatEntity(
  val sessionId: Long,
  val startDate: String,
  val emotion: String,
)


