package com.last.psychat.android.core.data.datasource.remote.login

import com.last.psychat.android.core.data.model.login.LoginResponse

interface LoginRemoteDataSource {
  suspend fun createLoginToken(): LoginResponse?
}
