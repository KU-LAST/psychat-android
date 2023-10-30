package com.last.psychat.android.core.domain.repository

import com.last.psychat.android.core.domain.entity.LoginEntity

interface LoginRepository {
  /**
   * 게스트 로그인 토큰 저장
   *
   * @param loginToken 게스트 로그인 토큰
   */
  suspend fun setLoginToken(loginToken: String)

  /**
   * 게스트 로그인 토큰 조회
   */
  suspend fun getLoginToken(): String

  /**
   * 게스트 로그인 토큰 생성
   */
  suspend fun createLoginToken(): LoginEntity?
}
