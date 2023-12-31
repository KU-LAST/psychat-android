package com.last.psychat.android.core.data.di

import com.last.psychat.android.core.data.repository.ChatRepositoryImpl
import com.last.psychat.android.core.data.repository.LoginRepositoryImpl
import com.last.psychat.android.core.data.repository.RecommendRepositoryImpl
import com.last.psychat.android.core.domain.repository.ChatRepository
import com.last.psychat.android.core.domain.repository.LoginRepository
import com.last.psychat.android.core.domain.repository.RecommendRepository
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

  @Binds
  @Singleton
  abstract fun bindChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository

  @Binds
  @Singleton
  abstract fun bindRecommendRepository(recommendRepositoryImpl: RecommendRepositoryImpl): RecommendRepository
}
