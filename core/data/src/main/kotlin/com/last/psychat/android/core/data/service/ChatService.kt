package com.last.psychat.android.core.data.service

import com.last.psychat.android.core.data.model.chat.ChatRequest
import com.last.psychat.android.core.data.model.chat.ChatResponse
import com.last.psychat.android.core.data.model.chat.EndChatRequest
import com.last.psychat.android.core.data.model.chat.PreviousChatListResponse
import com.last.psychat.android.core.data.model.chat.SessionResponse
import com.last.psychat.android.core.data.model.chat.EmotionResponse
import com.last.psychat.android.core.data.model.chat.PreviousChatDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
  @GET("previous-chats")
  suspend fun getPreviousChatList(): Response<PreviousChatListResponse>

  @GET("chat/{session-id}")
  suspend fun getPreviousChatDetail(
    @Path("session-id") sessionId: Long,
  ): Response<PreviousChatDetailResponse>

  @GET("start-chat")
  suspend fun startChatSession(): Response<SessionResponse>

  @POST("actual-chat")
  suspend fun sendChatMessage(
    @Body chatRequest: ChatRequest,
  ): Response<ChatResponse>

  @POST("end-chat")
  suspend fun endChatSession(
    @Body endChatRequest: EndChatRequest,
  ): Response<EmotionResponse>
}
