package com.last.psychat.android.core.data.util

internal data class ExceptionWrapper(
  val statusCode: Int? = null,
  override val message: String? = null,
  override val cause: Throwable? = null,
) : Exception(message, cause)
