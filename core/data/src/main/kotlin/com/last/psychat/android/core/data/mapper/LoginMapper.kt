package com.last.psychat.android.core.data.mapper

import com.last.psychat.android.core.data.model.LoginResponse
import com.last.psychat.android.core.domain.entity.LoginEntity

internal fun LoginResponse.toEntity() =
  LoginEntity(token = token)
