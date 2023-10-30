package com.last.psychat.android.core.data.datasource.remote

import com.last.psychat.android.core.data.model.LoginResponse

interface LoginRemoteDataSource {
  suspend fun createLoginToken(): LoginResponse?
}
