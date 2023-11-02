package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.local.login.LoginLocalDataSource
import com.last.psychat.android.core.data.datasource.remote.login.LoginRemoteDataSource
import com.last.psychat.android.core.data.mapper.login.toEntity
import com.last.psychat.android.core.domain.entity.login.LoginEntity
import com.last.psychat.android.core.domain.repository.LoginRepository
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
  private val localDataSource: LoginLocalDataSource,
  private val remoteDataSource: LoginRemoteDataSource,
) : LoginRepository {
  override suspend fun setLoginToken(loginToken: String) {
    localDataSource.setLoginToken(loginToken)
  }

  override suspend fun getLoginToken(): String {
    return localDataSource.getLoginToken()
  }

  override suspend fun createLoginToken(): LoginEntity? {
    return remoteDataSource.createLoginToken()?.toEntity()
  }
}
