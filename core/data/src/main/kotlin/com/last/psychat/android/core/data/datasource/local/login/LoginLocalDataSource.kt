package com.last.psychat.android.core.data.datasource.local.login

interface LoginLocalDataSource {
  suspend fun setLoginToken(loginToken: String)
  suspend fun getLoginToken(): String
}
