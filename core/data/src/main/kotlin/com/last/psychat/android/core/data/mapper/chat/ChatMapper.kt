package com.last.psychat.android.core.data.mapper.chat

import com.last.psychat.android.core.data.model.chat.BotMessage
import com.last.psychat.android.core.data.model.chat.ChatRequest
import com.last.psychat.android.core.data.model.chat.ChatResponse
import com.last.psychat.android.core.data.model.chat.ChattingList
import com.last.psychat.android.core.data.model.chat.EmotionResponse
import com.last.psychat.android.core.data.model.chat.EndChatRequest
import com.last.psychat.android.core.data.model.chat.PreviousChat
import com.last.psychat.android.core.data.model.chat.PreviousChatDetailResponse
import com.last.psychat.android.core.data.model.chat.PreviousChatListResponse
import com.last.psychat.android.core.data.model.chat.SessionResponse
import com.last.psychat.android.core.data.model.chat.UserMessage
import com.last.psychat.android.core.domain.entity.chat.BotMessageEntity
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.entity.chat.ChattingListEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatDetailEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatListEntity
import com.last.psychat.android.core.domain.entity.chat.SessionEntity
import com.last.psychat.android.core.domain.entity.chat.UserMessageEntity
import com.last.psychat.android.core.domain.entity.recommend.EmotionResponseEntity

internal fun ChatRequestEntity.toModel() =
  ChatRequest(
    sessionId = sessionId,
    messageContent = messageContent,
  )

internal fun ChatResponse.toEntity() =
  ChatResponseEntity(
    messageContent = messageContent,
  )

internal fun EndChatEntity.toModel() =
  EndChatRequest(
    sessionId = sessionId,
  )

internal fun PreviousChatDetailResponse.toEntity() =
  PreviousChatDetailEntity(
    chattingList = chattingList.toEntity(),
  )

internal fun PreviousChatListResponse.toEntity() =
  PreviousChatListEntity(
    previousChatList = previousChatList.map { it.toEntity() }
  )

internal fun PreviousChat.toEntity() =
  PreviousChatEntity(
    sessionId = sessionId,
    startDate = startDate,
    emotion = emotion,
  )

internal fun ChattingList.toEntity() =
  ChattingListEntity(
    userMessages = userMessages.map { it.toEntity() },
    botMessages = botMessages.map { it.toEntity() },
  )

internal fun UserMessage.toEntity() =
  UserMessageEntity(
    messageContent = messageContent,
    timestamp = timestamp
  )

internal fun BotMessage.toEntity() =
  BotMessageEntity(
    responseContent = responseContent,
    timestamp = timestamp,
  )

internal fun SessionResponse.toEntity() =
  SessionEntity(
    sessionId = sessionId,
  )

internal fun EmotionResponse.toEntity() =
  EmotionResponseEntity(
    emotion = emotion,
  )
