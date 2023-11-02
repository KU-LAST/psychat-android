package com.last.psychat.android.core.data.service

import com.last.psychat.android.core.data.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginService {
  @GET("member/login")
  suspend fun createLoginToken(): Response<LoginResponse>
}
