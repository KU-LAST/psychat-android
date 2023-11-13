package com.last.psychat.android.core.data.datasource.remote.chat

import com.last.psychat.android.core.data.model.chat.ChatRequest
import com.last.psychat.android.core.data.model.chat.ChatResponse
import com.last.psychat.android.core.data.model.chat.CheckEmotionIsJudgeResponse
import com.last.psychat.android.core.data.model.chat.EndChatRequest
import com.last.psychat.android.core.data.model.chat.PreviousChatListResponse
import com.last.psychat.android.core.data.model.chat.SessionResponse
import com.last.psychat.android.core.data.model.chat.EmotionResponse
import com.last.psychat.android.core.data.model.chat.PreviousChatDetailResponse
import com.last.psychat.android.core.data.service.ChatService
import com.last.psychat.android.core.data.util.extension.safeRequest
import javax.inject.Inject

internal class ChatRemoteDataSourceImpl @Inject constructor(
  private val service: ChatService,
) : ChatRemoteDataSource {
  override suspend fun getPreviousChatList(): PreviousChatListResponse? {
    return safeRequest {
      service.getPreviousChatList()
    }
  }

  override suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailResponse? {
    return safeRequest {
      service.getPreviousChatDetail(sessionId)
    }
  }

  override suspend fun startChatSession(): SessionResponse? {
    return safeRequest {
      service.startChatSession()
    }
  }

  override suspend fun sendChatMessage(chatRequest: ChatRequest): ChatResponse? {
    return safeRequest {
      service.sendChatMessage(chatRequest)
    }
  }

  override suspend fun checkEmotionIsJudged(endChatRequest: EndChatRequest): CheckEmotionIsJudgeResponse? {
    return safeRequest {
      service.checkEmotionIsJudged(endChatRequest)
    }
  }

  override suspend fun endChatSession(endChatRequest: EndChatRequest): EmotionResponse? {
    return safeRequest {
      service.endChatSession(endChatRequest)
    }
  }
}
