package com.last.psychat.android.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.last.pyschat.android.core.designsystem.theme.Gray900

@Composable
fun LoadingScreen(
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier,
  ) {
    CircularProgressIndicator(
      modifier = Modifier.align(Alignment.Center),
      color = Gray900,
    )
  }
}
