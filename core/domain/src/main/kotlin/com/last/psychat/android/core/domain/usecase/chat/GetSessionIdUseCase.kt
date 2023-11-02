package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.repository.ChatRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSessionIdUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(): Long {
    return repository.getSessionId()
  }
}