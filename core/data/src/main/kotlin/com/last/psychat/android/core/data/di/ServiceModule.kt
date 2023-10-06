package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.service.GuestLoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

  @Singleton
  @Provides
  internal fun provideGuestLoginService(retrofit: Retrofit): GuestLoginService {
    return retrofit.create(GuestLoginService::class.java)
  }
}
