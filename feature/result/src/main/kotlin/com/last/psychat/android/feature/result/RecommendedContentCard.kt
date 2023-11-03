package com.last.psychat.android.feature.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.last.pyschat.android.core.designsystem.theme.TextLSemiBold
import com.last.pyschat.android.core.designsystem.theme.TextMMedium

@Composable
fun RecommendedContentCard(
  modifier: Modifier = Modifier,
  date: String,
  title: String,
  thumbnailUrl: String,
  videoUrl: String,
) {
  val context = LocalContext.current

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
  ) {
    AsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 18.dp, horizontal = 16.dp)
        .weight(0.7f)
        .clip(RoundedCornerShape(16.dp)),
      model = ImageRequest.Builder(context)
        .data(thumbnailUrl)
        .build(),
      contentScale = ContentScale.Crop,
      contentDescription = stringResource(R.string.thumbnail),
    )
    Text(
      text = title,
      style = TextLSemiBold,
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .weight(0.2f),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )
    Text(
      text = date,
      style = TextMMedium,
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .weight(0.1f),
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
    )
  }
}