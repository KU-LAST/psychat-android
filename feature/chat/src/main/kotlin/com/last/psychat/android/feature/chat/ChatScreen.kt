package com.last.psychat.android.feature.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.core.ui.keyboardAsState
import com.last.psychat.android.feature.chat.components.ChatBubble
import com.last.psychat.android.feature.chat.components.ChatTopBar
import com.last.psychat.android.feature.chat.model.ChatMessageUiModel
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import kotlinx.coroutines.launch

// TODO 키보드가 올라오면 이전 채팅 내역이 보이지 않는 문제
// 스크롤을 항상 최하단으로 내려야 함
@Composable
internal fun ChatRoute(
  onNavigateBack: () -> Unit,
  navigateToResult: (String) -> Unit,
  viewModel: ChatViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  ObserveAsEvents(viewModel.eventFlow) { event ->
    when(event) {
      is ChatUiEvent.NavigateToResult -> {
        navigateToResult(uiState.emotion)
      }
      is ChatUiEvent.ShowToast -> {
        Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
      }
    }
  }

  ChatScreen(
    uiState = uiState,
    onNavigateBack = onNavigateBack,
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
  updateChatInputMessage: (String) -> Unit,
  sendChatMessage: () -> Unit,
  endChatSession: () -> Unit,
) {
  val listState = rememberLazyListState()
  val scope = rememberCoroutineScope()
  val isKeyboardOpen by keyboardAsState()
  var previousChat by remember { mutableStateOf(listOf<ChatMessageUiModel>()) }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      Column {
        Spacer(modifier = Modifier.height(16.dp))
        ChatTopBar(
          modifier = Modifier.height(56.dp),
          onNavigateBack = onNavigateBack,
          navigateToResult = endChatSession,
        )
        HorizontalDivider(color = Gray500)
        uiState.chatMessageList?.let {
          LazyColumn(
            modifier = Modifier
              .fillMaxHeight()
              .padding(bottom = 120.dp),
            state = listState
          ) {
            items(
              items = uiState.chatMessageList,
              key = { (it.message + " " + it.timestamp) },
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
            .weight(1f)
            .heightIn(min = 56.dp, max = 84.dp),
          value = uiState.chatInputMessage,
          singleLine = false,
          onValueChange = updateChatInputMessage,
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
          ),
        )
        IconButton(
          onClick = {
            if (uiState.chatInputMessage.isNotEmpty()) {
              sendChatMessage()
            }
          },
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
