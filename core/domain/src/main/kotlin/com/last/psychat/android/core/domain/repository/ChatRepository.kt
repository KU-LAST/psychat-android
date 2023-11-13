package com.last.psychat.android.core.domain.repository

import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.entity.chat.CheckEmotionIsJudgedEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatDetailEntity
import com.last.psychat.android.core.domain.entity.chat.PreviousChatListEntity
import com.last.psychat.android.core.domain.entity.chat.SessionEntity
import com.last.psychat.android.core.domain.entity.recommend.EmotionResponseEntity

interface ChatRepository {
  suspend fun setSessionId(sessionId: Long)

  suspend fun getSessionId(): Long

  suspend fun getPreviousChatList(): PreviousChatListEntity?

  suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailEntity?

  suspend fun startChatSession(): SessionEntity?

  suspend fun sendChatMessage(chatRequestEntity: ChatRequestEntity): ChatResponseEntity?

  suspend fun checkEmotionIsJudged(endChatEntity: EndChatEntity): CheckEmotionIsJudgedEntity?

  suspend fun endChatSession(endChatEntity: EndChatEntity): EmotionResponseEntity?
}
