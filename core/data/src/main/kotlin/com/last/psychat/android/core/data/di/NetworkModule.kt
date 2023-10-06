package com.last.psychat.android.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.last.psychat.android.core.data.BuildConfig
import com.last.psychat.android.core.data.datastore.DataStoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val MaxTimeoutMillis = 15000L

private val json = Json {
  encodeDefaults = true
  ignoreUnknownKeys = true
  prettyPrint = true
  isLenient = true
}

private val jsonRule = Json { json }

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

  @Singleton
  @Provides
  internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor { message ->
      Timber.tag("OkHttp").d(message)
    }.apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  }

  @Singleton
  @Provides
  internal fun provideRetrofitHttpClient(
    dataStoreProvider: DataStoreProvider,
    httpLoggingInterceptor: HttpLoggingInterceptor,
  ): Retrofit {
    val contentType = "application/json".toMediaType()
    val httpClient = OkHttpClient.Builder()
      .connectTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
      .addInterceptor { chain: Interceptor.Chain ->
        val request = chain.request().newBuilder()
          .addHeader("Content-Type", "application/json")
          .addHeader("X-GUEST-KEY", runBlocking { dataStoreProvider.getGuestLoginToken() })
          .build()
        chain.proceed(request)
      }
      .addInterceptor(httpLoggingInterceptor)
      .build()

    return Retrofit.Builder()
      .baseUrl(BuildConfig.SERVER_BASE_URL)
      .client(httpClient)
      .addConverterFactory(jsonRule.asConverterFactory(contentType))
      .build()
  }
}
