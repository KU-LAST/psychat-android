package com.last.psychat.android.core.data.datasource.local.chat

interface ChatLocalDataSource {
  suspend fun setSessionId(sessionId: Long)
  suspend fun getSessionId(): Long
}
