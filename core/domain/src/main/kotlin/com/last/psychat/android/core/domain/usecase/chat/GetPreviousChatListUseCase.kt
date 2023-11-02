package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.entity.chat.PreviousChatListEntity
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.util.PreviousChatListResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject

class GetPreviousChatListUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(): Result<PreviousChatListEntity> {
    return runSuspendCatching {
      repository.getPreviousChatList() ?: throw PreviousChatListResponseIsNull
    }
  }
}
