@file:OptIn(ExperimentalFoundationApi::class)

package com.last.psychat.android.feature.result

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentEntity
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.result.navigation.RESULT_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray200
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.H1
import com.last.pyschat.android.core.designsystem.theme.H5
import com.last.pyschat.android.core.designsystem.theme.TextXsRegular

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
    RecommendedContentEntity(
      date = "2023.10.12",
      title = "[PEOPLE in 세브란스] 몸이 보내는 우울증 신호 세 가지",
      thumbnailUrl = "https://img.youtube.com/vi/jME5_dk3mkQ/default.jpg",
      videoUrl = "https://www.youtube.com/watch?v=jME5_dk3mkQ",
    ),
  )


  val pageCount = 10
  val pagerState = rememberPagerState(pageCount = { pageCount })
  val pagerHeight = 320.dp

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Column(
      modifier = Modifier.verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier.height(32.dp))
      Text(
        text = "감정 판단 결과",
        style = H5,
      )
      Spacer(modifier.height(32.dp))
      Text(
        text = "우울",
        style = H1,
      )
      Spacer(modifier.weight(1f))
      // TODO 3개의 아이템이 화면에 보이는 HoriziontalPager 로 구현
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
      ) {
        Text(
          text = "추천 콘텐츠",
          style = H5,
          modifier = Modifier.padding(horizontal = 16.dp)
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
          modifier = Modifier.matchParentSize(),
        ) { page ->
          RecommendedContentCard(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp)
              .clip(RoundedCornerShape(16.dp))
              .background(Gray200),
            date = recommendedContentList[page].date,
            title = recommendedContentList[page].title,
            thumbnailUrl = recommendedContentList[page].thumbnailUrl,
            videoUrl = recommendedContentList[page].videoUrl,
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      PagerIndicator(
        pageCount = pageCount,
        pagerState = pagerState,
      )
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
