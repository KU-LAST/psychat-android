package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.datasource.local.LoginLocalDataSource
import com.last.psychat.android.core.data.datasource.local.LoginLocalDataSourceImpl
import com.last.psychat.android.core.data.datasource.remote.LoginRemoteDataSource
import com.last.psychat.android.core.data.datasource.remote.LoginRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

  @Binds
  @Singleton
  abstract fun bindLoginLocalDataSource(
    loginLocalDataSourceImpl: LoginLocalDataSourceImpl,
  ): LoginLocalDataSource

  @Binds
  @Singleton
  abstract fun bindLoginRemoteDataSource(
    loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl,
  ): LoginRemoteDataSource
}
