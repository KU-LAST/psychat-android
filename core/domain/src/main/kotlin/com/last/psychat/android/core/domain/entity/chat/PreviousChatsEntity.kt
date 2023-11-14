package com.last.psychat.android.core.domain.entity.chat

import androidx.compose.runtime.Stable

data class PreviousChatListEntity(
  val previousChatList: List<PreviousChatEntity>,
)

@Stable
data class PreviousChatEntity(
  val sessionId: Long,
  val startDate: String,
  val emotion: String,
)
