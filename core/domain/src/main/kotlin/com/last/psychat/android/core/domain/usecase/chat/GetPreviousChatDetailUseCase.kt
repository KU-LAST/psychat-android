package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.MessageEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.GetPreviousChatDetailResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPreviousChatDetailUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(sessionId: Long): Result<List<MessageEntity>> {
    return runSuspendCatching {
      val chatDetail = repository.getPreviousChatDetail(sessionId) ?: throw GetPreviousChatDetailResponseIsNull
      val combinedMessage = chatDetail.userMessages + chatDetail.botMessages
      combinedMessage.sortedBy { it.timestamp }
    }
  }
}
