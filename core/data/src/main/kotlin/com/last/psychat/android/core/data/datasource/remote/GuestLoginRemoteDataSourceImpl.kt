package com.last.psychat.android.core.data.datasource.remote

import com.last.psychat.android.core.data.model.GuestLoginTokenResponse
import com.last.psychat.android.core.data.service.GuestLoginService
import com.last.psychat.android.core.data.util.extension.safeRequest
import javax.inject.Inject

internal class GuestLoginRemoteDataSourceImpl @Inject constructor(
  private val service: GuestLoginService,
) : GuestLoginRemoteDataSource {
  override suspend fun createGuestLoginToken(): GuestLoginTokenResponse? {
    return safeRequest {
      service.createGuestLoginToken()
    }
  }
}
