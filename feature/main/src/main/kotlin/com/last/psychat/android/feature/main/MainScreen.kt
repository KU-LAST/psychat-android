package com.last.psychat.android.feature.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.components.MainTopBar
import com.last.psychat.android.feature.mapper.toUiModel
import com.last.pyschat.android.core.designsystem.theme.Gray300
import com.last.pyschat.android.core.designsystem.theme.Gray50

@Composable
internal fun MainRoute(
  navigateToChat: (Long) -> Unit,
  viewModel: MainViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  LaunchedEffect(viewModel) {
    viewModel.eventFlow.collect { event ->
      when (event) {
        is MainUiEvent.ShowToast -> {
          Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
        }
      }
    }
  }

  MainScreen(
    uiState = uiState,
    startChatSession = viewModel::startChatSession,
    navigateToChat = navigateToChat,
  )
}

@Composable
internal fun MainScreen(
  modifier: Modifier = Modifier,
  uiState: MainUiState,
  startChatSession: () -> Unit,
  navigateToChat: (Long) -> Unit,
) {
  LaunchedEffect(key1 = uiState.isSessionIdCreated) {
    if (uiState.isSessionIdCreated) {
      navigateToChat(uiState.sessionId)
    }
  }

  val previousChatList = listOf(
    PreviousChatEntity(
      sessionId = 10,
      startDate = "2023년 11월 5일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 9,
      startDate = "2023년 11월 4일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 8,
      startDate = "2023년 11월 3일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 7,
      startDate = "2023년 11월 2일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 6,
      startDate = "2023년 11월 1일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 5,
      startDate = "2023년 10월 31일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 4,
      startDate = "2023년 10월 30일",
      emotion = "우울"
    ),
    PreviousChatEntity(
      sessionId = 3,
      startDate = "2023년 10월 29일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 2,
      startDate = "2023년 10월 28일",
      emotion = "행복"
    ),
    PreviousChatEntity(
      sessionId = 1,
      startDate = "2023년 10월 27일",
      emotion = "우울"
    ),
  )

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Column {
      Spacer(modifier = Modifier.height(16.dp))
      MainTopBar()
      HorizontalDivider(color = Gray300)
      Box(
        modifier = Modifier
          .fillMaxHeight()
          .weight(1f)
          .padding(bottom = 56.dp)
      ) {
        LazyColumn {
          items(
            count = previousChatList.size,
            key = { index -> previousChatList[index].sessionId },
          ) { index ->
            PreviousCard(
              previousChat = previousChatList[index].toUiModel(),
              onClick = {},
            )
            HorizontalDivider(color = Gray300)
          }
        }
      }
      PsyChatButton(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .padding(horizontal = 24.dp),
        // onClick = startChatSession,
        onClick = {
          navigateToChat(uiState.sessionId)
        },
        text = stringResource(id = R.string.start_chat),
      )
      Spacer(modifier = Modifier.height(32.dp))
    }
  }
}
