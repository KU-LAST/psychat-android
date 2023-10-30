package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.repository.LoginRepositoryImpl
import com.last.psychat.android.core.domain.repository.LoginRepository
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
  abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
