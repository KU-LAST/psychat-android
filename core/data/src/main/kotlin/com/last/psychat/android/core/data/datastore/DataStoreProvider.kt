package com.last.psychat.android.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class DataStoreProvider @Inject constructor(
  private val dataStore: DataStore<Preferences>,
) {

  companion object {
    private const val LOGIN_TOKEN = "login_token"
    private const val SESSION_ID = "session_id"
  }

  private val prefKeyLoginToken = stringPreferencesKey(LOGIN_TOKEN)
  private val prefKeySessionId = longPreferencesKey(SESSION_ID)

  suspend fun setLoginToken(loginToken: String) {
    dataStore.edit { preferences ->
      preferences[prefKeyLoginToken] = loginToken
    }
  }

  suspend fun getLoginToken() = dataStore.data
    .catch { exception ->
      if (exception is IOException) emit(emptyPreferences())
      else throw exception
    }.first()[prefKeyLoginToken] ?: ""

  suspend fun setSessionId(sessionId: Long) {
    dataStore.edit { preferences ->
      preferences[prefKeySessionId] = sessionId
    }
  }

  suspend fun getSessionId() = dataStore.data
    .catch { exception ->
      if (exception is IOException) emit(emptyPreferences())
      else throw exception
    }.first()[prefKeySessionId] ?: -1
}
