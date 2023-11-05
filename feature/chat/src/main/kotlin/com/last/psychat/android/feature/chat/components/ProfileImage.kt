package com.last.psychat.android.feature.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.last.psychat.android.core.designsystem.R

@Composable
fun ProfileImage(
  modifier: Modifier = Modifier,
) {
  Image(
    painter = painterResource(id = R.drawable.chat_bot_profile_image), // 여기에 프로필 이미지 리소스 아이디를 넣어주세요.
    contentDescription = "Profile Image",
    modifier = modifier.clip(CircleShape),
  )
}
