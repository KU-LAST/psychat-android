package com.last.psychat.android.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.TextMRegular

@Composable
fun EmptyScreen(
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier,
  ) {
    Text(
      text = "이전 대화 목록이 존재하지 않아요\n대화를 시작 해주세요.",
      style = TextMRegular,
      color = Gray500,
      textAlign = TextAlign.Center,
      modifier = Modifier.align(Alignment.Center),
    )
  }
}
