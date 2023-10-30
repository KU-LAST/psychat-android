package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.local.LoginLocalDataSource
import com.last.psychat.android.core.data.datasource.remote.LoginRemoteDataSource
import com.last.psychat.android.core.data.mapper.toEntity
import com.last.psychat.android.core.domain.entity.LoginEntity
import com.last.psychat.android.core.domain.repository.LoginRepository
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
  private val loginLocalDataSource: LoginLocalDataSource,
  private val loginRemoteDataSource: LoginRemoteDataSource,
) : LoginRepository {
  override suspend fun setLoginToken(loginToken: String) {
    loginLocalDataSource.setLoginToken(loginToken)
  }

  override suspend fun getLoginToken(): String {
    return loginLocalDataSource.getLoginToken()
  }

  override suspend fun createLoginToken(): LoginEntity? {
    return loginRemoteDataSource.createLoginToken()?.toEntity()
  }
}
