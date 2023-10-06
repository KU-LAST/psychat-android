package com.last.psychat.android.core.data.datasource.local

interface GuestLoginLocalDataSource {
  suspend fun setGuestLoginToken(guestLoginToken: String)
  suspend fun getGuestLoginToken(): String
}
