package com.last.psychat.android.core.data.datasource.local.login

import com.last.psychat.android.core.data.datastore.DataStoreProvider
import javax.inject.Inject

internal class LoginLocalDataSourceImpl @Inject constructor(
  private val datastoreProvider: DataStoreProvider,
) : LoginLocalDataSource {

  override suspend fun setLoginToken(loginToken: String) {
    datastoreProvider.setLoginToken(loginToken)
  }

  override suspend fun getLoginToken(): String {
    return datastoreProvider.getLoginToken()
  }
}
