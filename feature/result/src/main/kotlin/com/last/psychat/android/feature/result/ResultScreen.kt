@file:OptIn(ExperimentalFoundationApi::class)

package com.last.psychat.android.feature.result

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.last.psychat.android.core.ui.Emotion
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.core.ui.components.LoadingScreen
import com.last.psychat.android.core.ui.components.NetworkErrorAlertDialog
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.result.components.ResultTopBar
import com.last.psychat.android.feature.result.navigation.RESULT_NAVIGATION_ROUTE
import com.last.psychat.core.util.getEmotionIndex
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
  modifier: Modifier = Modifier,
  viewModel: ResultViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  ObserveAsEvents(viewModel.eventFlow) { event ->
    when (event) {
      is ResultUiEvent.NavigateToMain -> {
        val options = NavOptions.Builder()
          .setPopUpTo(RESULT_NAVIGATION_ROUTE, inclusive = true)
          .build()
        navigateToMain(options)
      }
      is ResultUiEvent.NavigateToYoutube -> {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(event.videoUrl))
        context.startActivity(browserIntent)
      }
      is ResultUiEvent.ShowToast -> {
        Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
      }
    }
  }

  ResultScreen(
    uiState = uiState,
    getRecommendedContentList = viewModel::getRecommendedContentList,
    navigateToYoutube = viewModel::navigateToYoutube,
    navigateToMain = viewModel::navigateToMain,
    closeNetworkErrorDialog = viewModel::closeNetworkErrorDialog,
    modifier = modifier,
  )
}

@Composable
internal fun ResultScreen(
  uiState: ResultUiState,
  getRecommendedContentList: () -> Unit,
  navigateToYoutube: (String) -> Unit,
  navigateToMain: () -> Unit,
  closeNetworkErrorDialog: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val scrollState = rememberScrollState()

  val pageCount = uiState.recommendedContentList.size
  val pagerState = rememberPagerState(pageCount = { pageCount })
  val pagerHeight = 320.dp

  BackHandler {
    navigateToMain()
  }

  LaunchedEffect(key1 = Unit) {
    getRecommendedContentList()
  }

  if (uiState.isNetworkError) {
    NetworkErrorAlertDialog(
      title = stringResource(R.string.network_error_title),
      description = stringResource(R.string.network_error_description),
      onConfirmClick = {
        closeNetworkErrorDialog()
        getRecommendedContentList()
      },
    )
  }

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
      Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
          LoadingScreen(
            modifier = Modifier
              .fillMaxSize()
              .align(Alignment.Center),
          )
        }
        Column(
          modifier = Modifier.fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = stringResource(
                R.string.emotion_judgment_result,
                uiState.emotion.replace("+", " "),
              ),
              style = H3,
              textAlign = TextAlign.Center,
            )
          }
          Spacer(Modifier.weight(1f))
          Spacer(modifier.height(32.dp))
          if (getEmotionIndex(uiState.emotion) in 0..5) {
            AsyncImage(
              modifier = Modifier.size(96.dp),
              model = ImageRequest.Builder(context)
                .data(Emotion.values()[getEmotionIndex(uiState.emotion)].icon)
                .crossfade(true)
                .build(),
              contentDescription = stringResource(R.string.emotion_image_description),
            )
          }
          Spacer(modifier.weight(1f))
          Spacer(modifier.height(32.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
          ) {
            Text(
              text = if (getEmotionIndex(uiState.emotion) == 1) stringResource(R.string.positive_content_suggestion)
              else stringResource(R.string.negative_content_suggestion),
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
              text = stringResource(R.string.recommended_content),
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
            ) { page ->
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
//              thumbnailUrl = recommendedContentList[index].thumbnailUrl,
//              title = recommendedContentList[index].title,
//              date = recommendedContentList[index].date,
//              videoUrl = recommendedContentList[index].videoUrl,
                  thumbnailUrl = uiState.recommendedContentList[page].thumbnailUrl,
                  title = uiState.recommendedContentList[page].title,
                  date = uiState.recommendedContentList[page].date,
                  videoUrl = uiState.recommendedContentList[page].videoUrl,
                  onClick = navigateToYoutube,
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
        }
      }
      PsyChatButton(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .padding(horizontal = 24.dp),
        onClick = navigateToMain,
        text = context.getString(R.string.go_back),
      )
    }
  }
}
