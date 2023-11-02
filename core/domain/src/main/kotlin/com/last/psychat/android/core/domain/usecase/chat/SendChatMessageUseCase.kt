package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.ChatRequestEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.SendChatMessageResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendChatMessageUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(chatRequestEntity: ChatRequestEntity): Result<ChatResponseEntity> {
    return runSuspendCatching {
      repository.sendChatMessage(chatRequestEntity) ?: throw SendChatMessageResponseIsNull
    }
  }
}
