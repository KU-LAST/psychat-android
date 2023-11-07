package com.last.psychat.android.core.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalDensity

// https://stackoverflow.com/questions/68847559/how-can-i-detect-keyboard-opening-and-closing-in-jetpack-compose
@Composable
fun keyboardAsState(): State<Boolean> {
  val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
  return rememberUpdatedState(newValue = isImeVisible)
}
