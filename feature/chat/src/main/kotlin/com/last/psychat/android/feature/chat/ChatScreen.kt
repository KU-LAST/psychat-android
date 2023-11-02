package com.last.psychat.android.feature.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.chat.navigation.CHAT_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray50

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
  )
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  uiState: ChatUiState,
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
) {
  val context = LocalContext.current

  val chatMessages = listOf(
    ChatMessage(
      message = "안녕, 잘 지냈어?ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
      time = "오후 3:05",
      isUser = false // 상대방이 보낸 메시지
    ),
    ChatMessage(
      message = "응, 나도 잘 지냈어!",
      time = "오후 3:06",
      isUser = true // 사용자가 보낸 메시지
    ),
    ChatMessage(
      message = "오랜만이야, 뭐하고 지냈어?",
      time = "오후 3:07",
      isUser = false // 상대방이 보낸 메시지
    ),
  )

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      Column {
        Spacer(modifier = Modifier.height(16.dp))
        ChatTopBar(onNavigateBack = onNavigateBack)
        Box(modifier = Modifier.fillMaxSize()) {
          // TODO item key 추가
          LazyColumn {
            items(chatMessages) { chatMessage ->
              ChatBubble(chatMessage = chatMessage)
            }
          }

          PsyChatButton(
            onClick = {
              val options = NavOptions.Builder()
                .setPopUpTo(CHAT_NAVIGATION_ROUTE, inclusive = true)
                .build()
              navigateToResult(options)
            },
            text = context.getString(R.string.check_result),
            modifier = Modifier
              .align(Alignment.BottomCenter)
              .padding(bottom = 32.dp),
          )
        }
      }
    }
  }
}
