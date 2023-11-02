package com.last.psychat.android.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.last.psychat.android.feature.chat.model.ChatMessage
import com.last.psychat.core.util.toKoreanTimeString
import com.last.pyschat.android.core.designsystem.theme.Gray200
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.InfoS
import com.last.pyschat.android.core.designsystem.theme.TextSRegular

@Composable
fun ChatBubble(
  modifier: Modifier = Modifier,
  chatMessage: ChatMessage,
) {
  val messageArrangement = if (chatMessage.isUser) Arrangement.End else Arrangement.Start

  Row(
    modifier = modifier
      .padding(8.dp)
      .wrapContentHeight()
      .fillMaxWidth(),
    horizontalArrangement = messageArrangement,
    verticalAlignment = Alignment.Bottom
  ) {

    if (chatMessage.isUser) {
      TimeText(time = chatMessage.timestamp.toKoreanTimeString())
      Spacer(modifier = Modifier.width(8.dp))
      MessageBox(message = chatMessage.message)
    } else {
      MessageBox(message = chatMessage.message)
      Spacer(modifier = Modifier.width(8.dp))
      TimeText(time = chatMessage.timestamp.toKoreanTimeString())
    }
  }
}

@Composable
fun MessageBox(
  modifier: Modifier = Modifier,
  message: String,
) {
  val maxWidthDp = LocalConfiguration.current.screenWidthDp.dp * 2 / 3

  Box(
    modifier = modifier
      .widthIn(max = maxWidthDp)
      .clip(RoundedCornerShape(16.dp))
      .background(Gray200)
      .padding(8.dp),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = message,
      color = Gray900,
      modifier = Modifier.padding(all = 4.dp),
      style = TextSRegular
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
