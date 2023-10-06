package com.last.psychat.android.core.data.datasource.remote

import com.last.psychat.android.core.data.model.GuestLoginTokenResponse

interface GuestLoginRemoteDataSource {
  suspend fun createGuestLoginToken(): GuestLoginTokenResponse?
}
