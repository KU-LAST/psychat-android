package com.last.psychat.android.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.last.psychat.android.core.data.datastore.DataStoreProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val PSYCHAT_DATASTORE = "psychat_datastore"
private val Context.psychatDataStore: DataStore<Preferences> by preferencesDataStore(name = PSYCHAT_DATASTORE)

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

  @Provides
  @Singleton
  fun providePreferencesDataStore(@ApplicationContext context: Context) = context.psychatDataStore

  @Provides
  fun provideDataStore(dataStore: DataStore<Preferences>) = DataStoreProvider(dataStore)
}
