package com.last.psychat.android.core.domain.usecase.chat

import com.last.psychat.android.core.domain.repository.ChatRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetSessionIdUseCase @Inject constructor(
  private val repository: ChatRepository,
) {
  suspend operator fun invoke(sessionId: Long) {
    return repository.setSessionId(sessionId)
  }
}