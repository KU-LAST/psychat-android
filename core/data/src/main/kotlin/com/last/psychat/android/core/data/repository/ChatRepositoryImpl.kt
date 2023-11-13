package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.local.chat.ChatLocalDataSource
import com.last.psychat.android.core.data.datasource.remote.chat.ChatRemoteDataSource
import com.last.psychat.android.core.data.mapper.chat.toEntity
import com.last.psychat.android.core.data.mapper.chat.toModel
import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.entity.chat.CheckEmotionIsJudgedEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatDetailEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatListEntity
import com.last.psychat.android.core.domain.entity.chat.SessionEntity
import com.last.psychat.android.core.domain.entity.recommend.EmotionResponseEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
  private val localDataSource: ChatLocalDataSource,
  private val remoteDataSource: ChatRemoteDataSource,
) : ChatRepository {
  override suspend fun setSessionId(sessionId: Long) {
    localDataSource.setSessionId(sessionId)
  }

  override suspend fun getSessionId(): Long {
    return localDataSource.getSessionId()
  }

  override suspend fun getPreviousChatList(): PreviousChatListEntity? {
    return remoteDataSource.getPreviousChatList()?.toEntity()
  }

  override suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailEntity? {
    return remoteDataSource.getPreviousChatDetail(sessionId)?.toEntity()
  }

  override suspend fun startChatSession(): SessionEntity? {
    return remoteDataSource.startChatSession()?.toEntity()
  }

  override suspend fun sendChatMessage(chatRequestEntity: ChatRequestEntity): ChatResponseEntity? {
    return remoteDataSource.sendChatMessage(chatRequestEntity.toModel())?.toEntity()
  }

  override suspend fun checkEmotionIsJudged(endChatEntity: EndChatEntity): CheckEmotionIsJudgedEntity? {
    return remoteDataSource.checkEmotionIsJudged(endChatEntity.toModel())?.toEntity()
  }

  override suspend fun endChatSession(endChatEntity: EndChatEntity): EmotionResponseEntity? {
    return remoteDataSource.endChatSession(endChatEntity.toModel())?.toEntity()
  }
}
