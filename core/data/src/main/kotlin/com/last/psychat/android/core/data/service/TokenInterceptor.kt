package com.last.psychat.android.core.data.service

import com.last.psychat.android.core.data.datastore.DataStoreProvider
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor @Inject constructor(
  private val dataStoreProvider: DataStoreProvider,
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalHttpUrl = originalRequest.url

    val loginToken = runBlocking {
      dataStoreProvider.getLoginToken()
    }
    val newUrl = originalHttpUrl.newBuilder()
      .addQueryParameter("token", loginToken)
      .build()

    val newRequest = originalRequest.newBuilder()
      .url(newUrl)
      .build()

    return chain.proceed(newRequest)
  }
}
