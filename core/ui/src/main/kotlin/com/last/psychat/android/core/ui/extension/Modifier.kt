package com.last.psychat.android.core.ui.extension

import android.annotation.SuppressLint
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

// https://proandroiddev.com/remove-ripple-effect-from-clickable-and-toggleable-widget-in-jetpack-compose-16b154265283
@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
  clickable(
    indication = null,
    interactionSource = remember { MutableInteractionSource() },
  ) {
    onClick()
  }
}

// https://al-e-shevelev.medium.com/how-to-prevent-multiple-clicks-in-android-jetpack-compose-8e62224c9c5e
fun Modifier.clickableSingle(
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  onClick: () -> Unit,
) = composed(
  inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["enabled"] = enabled
    properties["onClickLabel"] = onClickLabel
    properties["role"] = role
    properties["onClick"] = onClick
  },
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Modifier.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    role = role,
    indication = LocalIndication.current,
    interactionSource = remember { MutableInteractionSource() },
  )
}
