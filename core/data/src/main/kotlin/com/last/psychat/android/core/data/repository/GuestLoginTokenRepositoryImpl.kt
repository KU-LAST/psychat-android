package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.local.GuestLoginLocalDataSource
import com.last.psychat.android.core.data.datasource.remote.GuestLoginRemoteDataSource
import com.last.psychat.android.core.data.mapper.toEntity
import com.last.psychat.android.core.domain.entity.GuestLoginTokenEntity
import com.last.psychat.android.core.domain.repository.GuestLoginTokenRepository
import javax.inject.Inject

internal class GuestLoginTokenRepositoryImpl @Inject constructor(
  private val localDataSource: GuestLoginLocalDataSource,
  private val remoteDataSource: GuestLoginRemoteDataSource,
) : GuestLoginTokenRepository {
  override suspend fun setGuestLoginToken(guestLoginToken: String) {
    localDataSource.setGuestLoginToken(guestLoginToken)
  }

  override suspend fun getGuestLoginToken(): String {
    return localDataSource.getGuestLoginToken()
  }

  override suspend fun createGuestLoginToken(): GuestLoginTokenEntity? {
    return remoteDataSource.createGuestLoginToken()?.toEntity()
  }
}
