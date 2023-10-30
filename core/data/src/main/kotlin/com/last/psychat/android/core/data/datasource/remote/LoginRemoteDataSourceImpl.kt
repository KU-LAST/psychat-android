package com.last.psychat.android.core.data.datasource.remote

import com.last.psychat.android.core.data.model.LoginResponse
import com.last.psychat.android.core.data.service.LoginService
import com.last.psychat.android.core.data.util.extension.safeRequest
import javax.inject.Inject

internal class LoginRemoteDataSourceImpl @Inject constructor(
  private val service: LoginService,
) : LoginRemoteDataSource {
  override suspend fun createLoginToken(): LoginResponse? {
    return safeRequest {
      service.createLoginToken()
    }
  }
}
