package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.service.ChatService
import com.last.psychat.android.core.data.service.LoginService
import com.last.psychat.android.core.data.service.RecommendService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

  @Singleton
  @Provides
  internal fun provideLoginService(
    @Named("AuthHttpClient")
    retrofit: Retrofit,
  ): LoginService {
    return retrofit.create(LoginService::class.java)
  }

  @Singleton
  @Provides
  internal fun provideChatService(
    @Named("HttpClient")
    retrofit: Retrofit,
  ): ChatService {
    return retrofit.create(ChatService::class.java)
  }

  @Singleton
  @Provides
  internal fun provideRecommendService(
    @Named("AuthHttpClient")
    retrofit: Retrofit,
  ): RecommendService {
    return retrofit.create(RecommendService::class.java)
  }
}
