package com.last.psychat.android.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
  @SerialName("token")
  val token: String,
)
