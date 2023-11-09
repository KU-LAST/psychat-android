@file:OptIn(ExperimentalFoundationApi::class)

package com.last.psychat.android.feature.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentEntity
import com.last.psychat.android.core.ui.Emotion
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.result.components.ResultTopBar
import com.last.psychat.android.feature.result.navigation.RESULT_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray200
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.H3
import com.last.pyschat.android.core.designsystem.theme.H5
import com.last.pyschat.android.core.designsystem.theme.TextXsRegular
import com.last.pyschat.android.core.designsystem.theme.Title

@Composable
internal fun ResultRoute(
  navigateToMain: (NavOptions) -> Unit,
) {
  ResultScreen(navigateToMain = navigateToMain)
}

@Composable
internal fun ResultScreen(
  modifier: Modifier = Modifier,
  navigateToMain: (NavOptions) -> Unit,
) {
  val context = LocalContext.current
  val scrollState = rememberScrollState()

  val recommendedContentList = listOf(
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "우울증으로 죽고 싶은 마음이 들 때, 우리는 어떡하면 좋을까요?",
      thumbnailUrl = "https://img.youtube.com/vi/BJUPDKoGnZo/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=BJUPDKoGnZo",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 우울증이 잘 오는 사람의 유형 네 가지!",
      thumbnailUrl = "https://img.youtube.com/vi/UcicbfYeSp4/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=UcicbfYeSp4",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "우울증에서 빠져나오는 가장 쉬운 방법",
      thumbnailUrl = "https://img.youtube.com/vi/WYmnqBaWtW0/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=WYmnqBaWtW0",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
  )

  val pageCount = 10
  val pagerState = rememberPagerState(pageCount = { pageCount })
  val pagerHeight = 280.dp

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Column(
      modifier = Modifier.verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(16.dp))
      ResultTopBar(modifier = Modifier.height(56.dp))
      HorizontalDivider(color = Gray500)
      Spacer(modifier.height(32.dp))
      Text(
        text = "지금은 ”우울“한 감정이시네요",
        style = H3,
      )
      Spacer(Modifier.weight(1f))
      Spacer(modifier.height(32.dp))
      AsyncImage(
        modifier = Modifier.size(96.dp),
        model = ImageRequest.Builder(context)
          .data(Emotion.values()[5].icon)
          .crossfade(true)
          .build(),
        contentDescription = "Mood Image",
      )
      Spacer(modifier.weight(1f))
      Spacer(modifier.height(32.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
      ) {
        Text(
          text = "다음 콘텐츠의 도움을 받아보는 것이 어떨까요?",
          style = Title,
          modifier = Modifier.padding(horizontal = 16.dp),
          maxLines = 2,
        )
      }
      Spacer(modifier.height(8.dp))
      HorizontalDivider(color = Gray500)
      Spacer(modifier.height(8.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
      ) {
        Text(
          text = "추천 콘텐츠",
          style = H5,
          modifier = Modifier.padding(horizontal = 16.dp),
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(pagerHeight),
        contentAlignment = Alignment.Center,
      ) {
        HorizontalPager(
          state = pagerState,
          contentPadding = PaddingValues(horizontal = 32.dp),
        ) { index ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(0.5.dp, Gray900),
            colors = CardDefaults.cardColors(containerColor = Gray200),
          ) {
            RecommendedContentCard(
              modifier = Modifier.fillMaxSize(),
              thumbnailUrl = recommendedContentList[index].thumbnailUrl,
              title = recommendedContentList[index].title,
              date = recommendedContentList[index].date,
              videoUrl = recommendedContentList[index].videoUrl,
            )
          }
        }
      }
      HorizontalPagerIndicator(
        pageCount = pageCount,
        currentPage = pagerState.currentPage,
        targetPage = pagerState.targetPage,
        currentPageOffsetFraction = pagerState.currentPageOffsetFraction,
      )
      Spacer(modifier = Modifier.height(8.dp))
      HorizontalDivider(color = Gray500)
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = stringResource(id = R.string.info_message),
        style = TextXsRegular,
        color = Gray500,
        modifier = Modifier.padding(bottom = 24.dp),
      )
      PsyChatButton(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .padding(horizontal = 24.dp),
        onClick = {
          val options = NavOptions.Builder()
            .setPopUpTo(RESULT_NAVIGATION_ROUTE, inclusive = true)
            .build()
          navigateToMain(options)
        },
        text = context.getString(R.string.go_back),
      )
      Spacer(Modifier.height(32.dp))
    }
  }
}
