package com.last.psychat.android.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.last.psychat.android.core.ui.Emotion
import com.last.psychat.android.feature.model.PreviousChat
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.H5
import com.last.pyschat.android.core.designsystem.theme.TextLMedium

@Composable
fun PreviousCard(
  previousChat: PreviousChat,
  onClick: (String) -> Unit,
) {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable { },
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(start = 12.dp, top = 16.dp),
      ) {
        Text(
          text = previousChat.startDate,
          style = H5,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          color = Gray900,
        )
        Spacer(Modifier.height(8.dp))
        Text(
          text = previousChat.emotion,
          style = TextLMedium,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          color = Gray900,
        )
        Spacer(Modifier.height(8.dp))
      }
      AsyncImage(
        modifier = Modifier
          .size(60.dp)
          .padding(end = 12.dp),
        model = ImageRequest.Builder(LocalContext.current)
          .data(Emotion.values()[previousChat.emotionIndex].icon)
          .crossfade(true)
          .build(),
        contentDescription = "Mood Image"
      )
    }
  }
}