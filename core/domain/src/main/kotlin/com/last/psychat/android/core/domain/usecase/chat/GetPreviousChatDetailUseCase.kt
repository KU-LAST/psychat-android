package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.PreviousChatDetailEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.GetPreviousChatDetailResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject

class GetPreviousChatDetailUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(sessionId: Long): Result<PreviousChatDetailEntity> {
    return runSuspendCatching {
      repository.getPreviousChatDetail(sessionId) ?: throw GetPreviousChatDetailResponseIsNull
    }
  }
}
