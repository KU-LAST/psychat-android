package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.datasource.local.LoginLocalDataSource
import com.last.psychat.android.core.data.datasource.local.LoginLocalDataSourceImpl
import com.last.psychat.android.core.data.datasource.remote.chat.ChatDataSource
import com.last.psychat.android.core.data.datasource.remote.chat.ChatDataSourceImpl
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
  abstract fun bindChatRemoteDataSource(
    chatDataSourceImpl: ChatDataSourceImpl,
  ): ChatDataSource

  @Binds
  @Singleton
  abstract fun bindRecommendRemoteDataSource(
    recommendDataSourceImpl: RecommendDataSourceImpl,
  ): RecommendDataSource
}

