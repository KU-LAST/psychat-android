package com.last.psychat.android.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.TextLMedium
import com.last.pyschat.android.core.designsystem.theme.White

@Composable
fun PsyChatButton(
  onClick: () -> Unit,
  text: String,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .clip(shape = RoundedCornerShape(50.dp))
      .clickable(onClick = onClick)
      .background(color = Gray900),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 16.dp),
      text = text,
      style = TextLMedium,
      color = White,
    )
  }
}
