package com.last.psychat.android.core.domain.usecase.login

import com.last.psychat.android.core.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetLoginTokenUseCase @Inject constructor(
  private val repository: LoginRepository,
) {
  suspend operator fun invoke(loginToken: String) {
    repository.setLoginToken(loginToken)
  }
}
