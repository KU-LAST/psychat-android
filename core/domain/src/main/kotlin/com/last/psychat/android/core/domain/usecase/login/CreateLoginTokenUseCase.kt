package com.last.psychat.android.core.domain.usecase.login

import com.last.psychat.android.core.domain.entity.LoginEntity
import com.last.psychat.android.core.domain.repository.LoginRepository
import com.last.psychat.android.core.domain.util.runSuspendCatching
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val CreateLoginTokenResponseIsNull = IOException("Create LoginToken API response is null.")

@Singleton
class CreateLoginTokenUseCase @Inject constructor(
  private val repository: LoginRepository,
) {
  suspend operator fun invoke(): Result<LoginEntity> {
    return runSuspendCatching {
      repository.createLoginToken() ?: throw CreateLoginTokenResponseIsNull
    }
  }
}
