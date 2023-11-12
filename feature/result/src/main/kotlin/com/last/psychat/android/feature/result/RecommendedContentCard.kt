package com.last.psychat.android.feature.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.last.psychat.android.core.ui.extension.clickableSingle
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.TextMSemiBold
import com.last.pyschat.android.core.designsystem.theme.TextXsRegular

@Composable
fun RecommendedContentCard(
  modifier: Modifier = Modifier,
  thumbnailUrl: String,
  title: String,
  date: String,
  videoUrl: String,
  onClick: (String) -> Unit,
) {
  val context = LocalContext.current

  Column(
    modifier = modifier.clickableSingle(onClick = { onClick(videoUrl) }),
    verticalArrangement = Arrangement.Center,
  ) {
    AsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.7f)
        .clipToBounds(),
      model = ImageRequest.Builder(context)
        .data(thumbnailUrl)
        .build(),
      contentScale = ContentScale.Crop,
      contentDescription = stringResource(R.string.thumbnail),
    )
    Text(
      text = title,
      style = TextMSemiBold,
      color = Gray900,
      modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        .weight(0.2f),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.1f),
      horizontalArrangement = Arrangement.End,
    ) {
      Text(
        text = date,
        style = TextXsRegular,
        color = Gray500,
        modifier = Modifier.padding(horizontal = 16.dp),
      )
    }
  }
}
