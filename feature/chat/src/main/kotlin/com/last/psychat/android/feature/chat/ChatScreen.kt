package com.last.psychat.android.feature.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.last.pyschat.android.core.designsystem.theme.Gray50

// TODO 뒤로 가기 버튼이 있어야 하는지 결정
// 종료된 세션이 아니면 다시 돌아와서 이어가야 할 것 같은데
// TODO 키보드가 올라오면 이전 채팅 내역이 보이지 않는 문제
@Composable
internal fun ChatRoute(
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
  viewModel: ChatViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  LaunchedEffect(viewModel) {
    viewModel.eventFlow.collect { event ->
      when (event) {
        is ChatUiEvent.ShowToast -> {
          Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
        }
      }
    }
  }

  ChatScreen(
    uiState = uiState,
    onNavigateBack = onNavigateBack,
    navigateToResult = navigateToResult,
    updateChatInputMessage = viewModel::updateChatInputMessage,
    sendChatMessage = viewModel::sendChatMessage,
  )
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  uiState: ChatUiState,
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
  updateChatInputMessage: (String) -> Unit,
  sendChatMessage: () -> Unit,
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      Column {
        Spacer(modifier = Modifier.height(16.dp))
        ChatTopBar(onNavigateBack = onNavigateBack)
        Box(modifier = Modifier.fillMaxSize()) {
          LazyColumn {
            items(
              count = uiState.chatMessageList.size,
              key = { index -> uiState.chatMessageList[index].timestamp },
            ) { index ->
              ChatBubble(chatMessage = uiState.chatMessageList[index])
            }
          }

          OutlinedTextField(
            modifier = Modifier
              .fillMaxWidth()
              .align(Alignment.BottomCenter)
              .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp,
              ),
            value = uiState.chatInputMessage,
            singleLine = true,
            onValueChange = updateChatInputMessage,
            keyboardOptions = KeyboardOptions(
              imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
              onDone = {
                if (uiState.chatInputMessage.isNotEmpty()) {
                  sendChatMessage()
                }
              }
            )
          )
        }
      }
    }
  }
}
