package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.remote.chat.ChatDataSource
import com.last.psychat.android.core.data.mapper.chat.toEntity
import com.last.psychat.android.core.data.mapper.chat.toModel
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatDetailEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatListEntity
import com.last.psychat.android.core.domain.entity.chat.SessionEntity
import com.last.psychat.android.core.domain.entity.recommend.EmotionResponseEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
  private val dataSource: ChatDataSource,
) : ChatRepository {
  override suspend fun getPreviousChatList(): PreviousChatListEntity? {
    return dataSource.getPreviousChatList()?.toEntity()
  }

  override suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailEntity? {
    return dataSource.getPreviousChatDetail(sessionId)?.toEntity()
  }

  override suspend fun startChatSession(): SessionEntity? {
    return dataSource.startChatSession()?.toEntity()
  }

  override suspend fun sendChatMessage(chatRequestEntity: ChatRequestEntity): ChatResponseEntity? {
    return dataSource.sendChatMessage(chatRequestEntity.toModel())?.toEntity()
  }

  override suspend fun endChatSession(endChatEntity: EndChatEntity): EmotionResponseEntity? {
    return dataSource.endChatSession(endChatEntity.toModel())?.toEntity()
  }
}
