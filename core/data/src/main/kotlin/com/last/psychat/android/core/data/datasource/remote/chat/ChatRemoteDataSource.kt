package com.last.psychat.android.core.data.datasource.remote.chat

import com.last.psychat.android.core.data.model.chat.ChatRequest
import com.last.psychat.android.core.data.model.chat.ChatResponse
import com.last.psychat.android.core.data.model.chat.EndChatRequest
import com.last.psychat.android.core.data.model.chat.PreviousChatListResponse
import com.last.psychat.android.core.data.model.chat.SessionResponse
import com.last.psychat.android.core.data.model.chat.EmotionResponse
import com.last.psychat.android.core.data.model.chat.PreviousChatDetailResponse

interface ChatRemoteDataSource {
  suspend fun getPreviousChatList(): PreviousChatListResponse?

  suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailResponse?

  suspend fun startChatSession(): SessionResponse?

  suspend fun sendChatMessage(chatRequest: ChatRequest): ChatResponse?

  suspend fun endChatSession(endChatRequest: EndChatRequest): EmotionResponse?
}