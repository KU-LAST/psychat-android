package com.last.psychat.android.feature.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.last.psychat.android.feature.chat.R
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.TextMMedium

@Composable
fun ChatTopBar(
  modifier: Modifier = Modifier,
  onNavigateBack: () -> Unit,
  navigateToResult: () -> Unit,
) {
  val context = LocalContext.current
  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    contentAlignment = Alignment.Center
  ) {
    ProfileImage(modifier = Modifier.size(56.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
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
//      PsyChatButton(
//        modifier = Modifier
//          .wrapContentWidth()
//          .height(36.dp),
//        onClick = navigateToResult,
//        text = "결과 보기",
//      )
      Text(
        text = "결과 보기",
        style = TextMMedium,
        color = Gray900,
        modifier = Modifier.clickable(
          onClick = navigateToResult,
        )
      )
    }
  }
}
