package com.last.psychat.android.core.domain.usecase.login

import com.last.psychat.android.core.domain.repository.GuestLoginTokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetGuestLoginTokenUseCase @Inject constructor(
  private val repository: GuestLoginTokenRepository,
) {
  suspend operator fun invoke(guestLoginToken: String) {
    repository.setGuestLoginToken(guestLoginToken)
  }
}
