package com.last.psychat.android.core.data.service

import com.last.psychat.android.core.data.model.GuestLoginTokenResponse
import retrofit2.Response
import retrofit2.http.POST

interface GuestLoginService {
  @POST("v1/users/guests")
  suspend fun createGuestLoginToken(): Response<GuestLoginTokenResponse>
}
