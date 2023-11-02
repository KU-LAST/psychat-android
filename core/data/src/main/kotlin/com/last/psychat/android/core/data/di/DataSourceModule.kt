package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.datasource.local.chat.ChatLocalDataSource
import com.last.psychat.android.core.data.datasource.local.chat.ChatLocalDataSourceImpl
import com.last.psychat.android.core.data.datasource.local.login.LoginLocalDataSource
import com.last.psychat.android.core.data.datasource.local.login.LoginLocalDataSourceImpl
import com.last.psychat.android.core.data.datasource.remote.chat.ChatRemoteDataSource
import com.last.psychat.android.core.data.datasource.remote.chat.ChatRemoteDataSourceImpl
import com.last.psychat.android.core.data.datasource.remote.login.LoginRemoteDataSource
import com.last.psychat.android.core.data.datasource.remote.login.LoginRemoteDataSourceImpl
import com.last.psychat.android.core.data.datasource.remote.recommend.RecommendDataSource
import com.last.psychat.android.core.data.datasource.remote.recommend.RecommendDataSourceImpl
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

  @Binds
  @Singleton
  abstract fun bindChatLocalDataSource(
    chatLocalDataSourceImpl: ChatLocalDataSourceImpl,
  ): ChatLocalDataSource

  @Binds
  @Singleton
  abstract fun bindChatRemoteDataSource(
    chatRemoteDataSourceImpl: ChatRemoteDataSourceImpl,
  ): ChatRemoteDataSource

  @Binds
  @Singleton
  abstract fun bindRecommendRemoteDataSource(
    recommendDataSourceImpl: RecommendDataSourceImpl,
  ): RecommendDataSource
}

