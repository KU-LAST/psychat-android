package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.entity.recommend.EmotionResponseEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.EndChatSessionResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EndChatSessionUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(endChatEntity: EndChatEntity): Result<EmotionResponseEntity> {
    return runSuspendCatching {
      repository.endChatSession(endChatEntity) ?: throw EndChatSessionResponseIsNull
    }
  }
}
