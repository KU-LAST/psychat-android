package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.CheckEmotionIsJudgedEntity
import com.last.psychat.android.core.domain.entity.chat.EndChatEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.CheckEmotionIsJudgedResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckEmotionIsJudgedUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(endChatEntity: EndChatEntity): Result<CheckEmotionIsJudgedEntity> {
    return runSuspendCatching {
      repository.checkEmotionIsJudged(endChatEntity) ?: throw CheckEmotionIsJudgedResponseIsNull
    }
  }
}
