package com.last.psychat.android.core.ui

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
  class StringResource(@StringRes val resId: Int) : UiText()
  data class DirectString(val value: String) : UiText()

  fun asString(context: Context) = when (this) {
    is StringResource -> context.getString(resId)
    is DirectString -> value
  }
}
