package com.last.psychat.android.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class DataStoreProvider @Inject constructor(
  private val dataStore: DataStore<Preferences>,
) {

  companion object {
    private const val GUEST_LOGIN_TOKEN = "guest_login_token"
  }

  private val prefKeyGuestLoginToken = stringPreferencesKey(GUEST_LOGIN_TOKEN)

  suspend fun setGuestLoginToken(guestLoginToken: String) {
    dataStore.edit { preferences ->
      preferences[prefKeyGuestLoginToken] = guestLoginToken
    }
  }

  suspend fun getGuestLoginToken() = dataStore.data
    .catch { exception ->
      if (exception is IOException) emit(emptyPreferences())
      else throw exception
    }.first()[prefKeyGuestLoginToken] ?: ""
}
