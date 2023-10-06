package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.repository.GuestLoginTokenRepositoryImpl
import com.last.psychat.android.core.domain.repository.GuestLoginTokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun bindGuestLoginTokenRepository(
    guestLoginTokenRepositoryImpl: GuestLoginTokenRepositoryImpl,
  ): GuestLoginTokenRepository
}
