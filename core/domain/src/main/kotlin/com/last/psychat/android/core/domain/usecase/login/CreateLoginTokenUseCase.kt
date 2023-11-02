package com.last.psychat.android.core.domain.usecase.login

import com.last.psychat.android.core.domain.entity.login.LoginEntity
import com.last.psychat.android.core.domain.repository.LoginRepository
import com.last.psychat.android.core.domain.util.CreateLoginTokenResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

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
