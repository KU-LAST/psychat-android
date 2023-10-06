package com.last.psychat.android.core.domain.usecase.login

import com.last.psychat.android.core.domain.entity.GuestLoginTokenEntity
import com.last.psychat.android.core.domain.repository.GuestLoginTokenRepository
import com.last.psychat.android.core.domain.util.runSuspendCatching
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val CreateGuestLoginTokenResponseIsNull = IOException("Create GuestLoginToken API response is null.")

@Singleton
class CreateGuestLoginTokenUseCase @Inject constructor(
  private val repository: GuestLoginTokenRepository,
) {
  suspend operator fun invoke(): Result<GuestLoginTokenEntity> {
    return runSuspendCatching {
      repository.createGuestLoginToken() ?: throw CreateGuestLoginTokenResponseIsNull
    }
  }
}
