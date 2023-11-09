package com.last.psychat.android.core.data.datasource.local.chat

import com.last.psychat.android.core.data.datastore.DataStoreProvider
import javax.inject.Inject

internal class ChatLocalDataSourceImpl @Inject constructor(
  private val datastoreProvider: DataStoreProvider,
) : ChatLocalDataSource {

  override suspend fun setSessionId(sessionId: Long) {
    datastoreProvider.setSessionId(sessionId)
  }

  override suspend fun getSessionId(): Long {
    return datastoreProvider.getSessionId()
  }
}
