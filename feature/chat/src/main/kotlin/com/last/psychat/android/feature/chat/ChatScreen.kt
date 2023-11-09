package com.last.psychat.android.feature.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.last.psychat.android.core.ui.keyboardAsState
import com.last.psychat.android.feature.chat.components.ChatBubble
import com.last.psychat.android.feature.chat.components.ChatTopBar
import com.last.psychat.android.feature.chat.model.ChatMessage
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import kotlinx.coroutines.launch

// TODO 뒤로 가기 버튼이 있어야 하는지 결정
// 종료된 세션이 아니면 다시 돌아와서 이어가야 할 것 같은데
// TODO 키보드가 올라오면 이전 채팅 내역이 보이지 않는 문제
// 스크롤을 항상 최하단으로 내려야 함
@Composable
internal fun ChatRoute(
  onNavigateBack: () -> Unit,
  navigateToResult: () -> Unit,
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
    endChatSession = viewModel::endChatSession,
  )
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  uiState: ChatUiState,
  onNavigateBack: () -> Unit,
  navigateToResult: () -> Unit,
  updateChatInputMessage: (String) -> Unit,
  sendChatMessage: () -> Unit,
  endChatSession: () -> Unit,
) {
  val listState = rememberLazyListState()
  val scope = rememberCoroutineScope()
  val isKeyboardOpen by keyboardAsState()
  var previousChat by remember { mutableStateOf(listOf<ChatMessage>()) }

  LaunchedEffect(key1 = uiState.isSessionEnd) {
    if(uiState.isSessionEnd) {
      navigateToResult()
    }
  }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      Column {
        Spacer(modifier = Modifier.height(16.dp))
        // TODO 뒤로 이동 했다가 다시 바로 채팅 화면으로 이동되지 않도록
        ChatTopBar(
          modifier = Modifier.height(56.dp),
          onNavigateBack = onNavigateBack,
          navigateToResult = endChatSession,
        )
        HorizontalDivider(color = Gray500)
        Box(modifier = Modifier.fillMaxSize()) {
          uiState.chatMessageList?.let {
            LazyColumn(
              state = listState
            ) {
              items(
                items = uiState.chatMessageList,
                key = { it.timestamp },
              ) { chatMessage ->
                ChatBubble(chatMessage = chatMessage)
              }

              if (isKeyboardOpen || previousChat.size != it.size) {
                scope.launch {
                  listState.scrollToItem(it.size - 1)
                }
              }
            }
            previousChat = it
          }
          Row(
            modifier = Modifier
              .padding(bottom = 32.dp)
              .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            OutlinedTextField(
              modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
              value = uiState.chatInputMessage,
              singleLine = false,
              onValueChange = updateChatInputMessage,
              keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
              ),
            )
            IconButton(
              onClick = {
                if (uiState.chatInputMessage.isNotEmpty()) {
                  sendChatMessage()
                }
              }
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Outlined.Send,
                contentDescription = "Send Message",
              )
            }
          }
        }
      }
    }
  }
}
