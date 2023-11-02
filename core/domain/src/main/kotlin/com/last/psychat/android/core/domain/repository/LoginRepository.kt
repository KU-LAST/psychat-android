package com.last.psychat.android.core.domain.repository

import com.last.psychat.android.core.domain.entity.login.LoginEntity

interface LoginRepository {
  /**
   * 로그인 토큰 저장
   *
   * @param loginToken 로그인 토큰
   */
  suspend fun setLoginToken(loginToken: String)

  /**
   * 로그인 토큰 조회
   */
  suspend fun getLoginToken(): String

  /**
   * 로그인 토큰 생성
   */
  suspend fun createLoginToken(): LoginEntity?
}
