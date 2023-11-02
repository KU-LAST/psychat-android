package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.SessionEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.StartChatSessionResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject

class StartChatSessionUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(): Result<SessionEntity> {
    return runSuspendCatching {
      repository.startChatSession() ?: throw StartChatSessionResponseIsNull
    }
  }
}
