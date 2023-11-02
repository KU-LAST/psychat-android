package com.last.psychat.android.core.data.mapper.login

import com.last.psychat.android.core.data.model.login.LoginResponse
import com.last.psychat.android.core.domain.entity.login.LoginEntity

internal fun LoginResponse.toEntity() =
  LoginEntity(token = token)
