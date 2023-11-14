package com.last.psychat.android.feature.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.last.psychat.android.core.ui.components.ProfileImage
import com.last.psychat.android.feature.chat.model.ChatMessageUiModel
import com.last.psychat.core.util.extension.formatTime
import com.last.pyschat.android.core.designsystem.theme.Gray200
import com.last.pyschat.android.core.designsystem.theme.Gray300
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.InfoS
import com.last.pyschat.android.core.designsystem.theme.TextSRegular

@Composable
fun ChatBubble(
  modifier: Modifier = Modifier,
  chatMessage: ChatMessageUiModel,
) {
  val messageArrangement = if (chatMessage.isUser) Arrangement.End else Arrangement.Start

  Row(
    modifier = modifier
      .padding(8.dp)
      .wrapContentHeight()
      .fillMaxWidth(),
    horizontalArrangement = messageArrangement,
    verticalAlignment = Alignment.Bottom,
  ) {
    if (chatMessage.isUser) {
      TimeText(time = chatMessage.timestamp.formatTime())
      // TimeText(time = chatMessage.timestamp)
      Spacer(modifier = Modifier.width(8.dp))
      MessageBox(
        message = chatMessage.message,
        isUser = true,
      )
    } else {
      ProfileImage(
        modifier = Modifier
          .align(Alignment.Top)
          .size(48.dp),
      )
      Spacer(modifier = Modifier.width(8.dp))
      MessageBox(
        message = chatMessage.message,
        isUser = false,
      )
      Spacer(modifier = Modifier.width(8.dp))
      TimeText(time = chatMessage.timestamp.formatTime())
      // TimeText(time = chatMessage.timestamp)
    }
  }
}

@Composable
fun MessageBox(
  modifier: Modifier = Modifier,
  message: String,
  isUser: Boolean,
) {
  val maxWidthDp = LocalConfiguration.current.screenWidthDp.dp * 2 / 3

  Box(
    modifier = modifier
      .widthIn(max = if (isUser) maxWidthDp else maxWidthDp - 56.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(if (isUser) Gray200 else Gray300)
      .padding(8.dp),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = message,
      color = Gray900,
      modifier = Modifier.padding(all = 4.dp),
      style = TextSRegular,
    )
  }
}

@Composable
fun TimeText(time: String) {
  Text(
    text = time,
    color = Gray500,
    style = InfoS,
  )
}
