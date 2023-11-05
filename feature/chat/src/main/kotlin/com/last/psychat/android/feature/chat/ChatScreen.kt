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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.last.psychat.android.feature.chat.model.ChatMessage
import com.last.pyschat.android.core.designsystem.theme.Gray300
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
    endChatSession = viewModel::endChatSession,
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
  endChatSession: () -> Unit,
) {
  val chatMessageList = listOf(
    ChatMessage(
      message = "나 요즘 너무 힘들어",
      timestamp = "오전 10시 12분",
      isUser = true,
    ),
    ChatMessage(
      message = "네 그렇군요, 듣고 있으니 계속 말씀하세요",
      timestamp = "오전 10시 13분",
      isUser = false,
    ),
    ChatMessage(
      message = "학교 팀플에 취업 준비에 너무 할게 많아서 스트레스 받아. 그냥 좀 쉬고 싶어",
      timestamp = "오전 10시 14분",
      isUser = true,
    ),
    ChatMessage(
      message = "그런 일이 있으셨군요. 정말 힘드시겠어요. 제가 옆에서 힘이 되어 드릴께요",
      timestamp = "오전 10시 15분",
      isUser = false,
    ),
    ChatMessage(
      message = "어떻게 스트레스를 그나마 조금이라도 해소할 수 있을까?",
      timestamp = "오전 10시 16분",
      isUser = true,
    ),
    ChatMessage(
      message = "쉬는 시간에 가볍게 산책을 다녀오시는 건 어떨까요?",
      timestamp = "오전 10시 17분",
      isUser = false,
    ),
  )

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
        HorizontalDivider(color = Gray300)
        Box(modifier = Modifier.fillMaxSize()) {
          LazyColumn {
            items(
              // count = uiState.chatMessageList.size,
              count = chatMessageList.size,
              key = { index -> chatMessageList[index].timestamp },
            ) { index ->
              ChatBubble(chatMessage = chatMessageList[index])
            }
          }
          Row(
            modifier = Modifier
              .padding(bottom = 32.dp)
              .align(Alignment.BottomCenter),
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
