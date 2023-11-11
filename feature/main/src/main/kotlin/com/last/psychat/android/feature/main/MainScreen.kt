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
import androidx.compose.foundation.lazy.items
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
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.components.MainTopBar
import com.last.psychat.android.feature.mapper.toUiModel
import com.last.pyschat.android.core.designsystem.theme.Gray300
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500

@Composable
internal fun MainRoute(
  navigateToChat: (Long) -> Unit,
  viewModel: MainViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  ObserveAsEvents(viewModel.eventFlow) { event ->
    when(event) {
      is MainUiEvent.NavigateToChat -> {
        navigateToChat(event.sessionId)
      }
      is MainUiEvent.ShowToast -> {
        Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
      }
    }
  }

  MainScreen(
    uiState = uiState,
    checkLoginToken = viewModel::checkLoginToken,
    // getPreviousChatList = viewModel::getPreviousChatList,
    startChatSession = viewModel::startChatSession,
    resumeChatSession = viewModel::resumeChatSession,
  )
}

// TODO 스켈레톤 UI 구성
@Composable
internal fun MainScreen(
  modifier: Modifier = Modifier,
  uiState: MainUiState,
  checkLoginToken: () -> Unit,
  // getPreviousChatList: () -> Unit,
  startChatSession: () -> Unit,
  resumeChatSession: (Long) -> Unit,
) {
  LaunchedEffect(key1 = Unit) {
    checkLoginToken()
  }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Column {
      Spacer(modifier = Modifier.height(16.dp))
      MainTopBar()
      HorizontalDivider(color = Gray500)
      Box(
        modifier = Modifier
          .fillMaxHeight()
          .weight(1f)
          .padding(bottom = 32.dp),
      ) {
        LazyColumn {
          items(
            items = uiState.previousChatList,
            key = { it.sessionId },
          ) { previousChat ->
            PreviousCard(
              previousChat = previousChat.toUiModel(),
              onClick = { sessionId ->
                resumeChatSession(sessionId)
              },
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
        onClick = startChatSession,
        text = stringResource(id = R.string.start_chat),
      )
      Spacer(modifier = Modifier.height(32.dp))
    }
  }
}
