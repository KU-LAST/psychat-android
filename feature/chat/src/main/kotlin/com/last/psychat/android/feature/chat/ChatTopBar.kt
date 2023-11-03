package com.last.psychat.android.feature.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.pyschat.android.core.designsystem.theme.Gray900

@Composable
fun ChatTopBar(
  modifier: Modifier = Modifier,
  onNavigateBack: () -> Unit,
  navigateToResult: () -> Unit,
) {
  val context = LocalContext.current
  Row(
    modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    IconButton(
      onClick = onNavigateBack,
      modifier = Modifier
        .width(32.dp)
        .aspectRatio(1f),
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
        contentDescription = context.getString(R.string.arrow_forward_descrption),
        tint = Gray900,
      )
    }
    Spacer(modifier = modifier.weight(1f))
    PsyChatButton(
      modifier = Modifier
        .wrapContentWidth()
        .height(36.dp),
      onClick = navigateToResult,
      text = "결과 보기"
    )
  }
}