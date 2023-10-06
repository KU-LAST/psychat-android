package com.last.psychat.android.core.data.mapper

import com.last.psychat.android.core.data.model.GuestLoginTokenResponse
import com.last.psychat.android.core.domain.entity.GuestLoginTokenEntity

internal fun GuestLoginTokenResponse.toEntity() =
  GuestLoginTokenEntity(key = key)
