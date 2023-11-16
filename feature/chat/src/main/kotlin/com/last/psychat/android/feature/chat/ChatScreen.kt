package com.last.psychat.android.feature.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.core.ui.components.LoadingScreen
import com.last.psychat.android.core.ui.extension.noRippleClickable
import com.last.psychat.android.core.ui.keyboardAsState
import com.last.psychat.android.feature.chat.components.ChatBubble
import com.last.psychat.android.feature.chat.components.ChatTopBar
import com.last.psychat.android.feature.chat.model.ChatMessageUiModel
import com.last.psychat.core.util.extension.formatDate
import com.last.psychat.core.util.getCurrentTime
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.TextMRegular
import com.last.pyschat.android.core.designsystem.theme.TextXsRegular
import kotlin.random.Random
import kotlinx.coroutines.launch

// TODO 채팅을 보내면 텍스트 필드가 바로 비워지게?
@Composable
internal fun ChatRoute(
  onNavigateBack: () -> Unit,
  navigateToResult: (String) -> Unit,
  viewModel: ChatViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  ObserveAsEvents(viewModel.eventFlow) { event ->
    when (event) {
      is ChatUiEvent.NavigateToResult -> {
        navigateToResult(event.emotion)
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
    checkEmotionIsJudged = viewModel::checkEmotionIsJudged,
  )
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  uiState: ChatUiState,
  onNavigateBack: () -> Unit,
  updateChatInputMessage: (String) -> Unit,
  sendChatMessage: () -> Unit,
  checkEmotionIsJudged: () -> Unit,
) {
  val listState = rememberLazyListState()
  val scope = rememberCoroutineScope()
  val isKeyboardOpen by keyboardAsState()
  var previousChat by remember { mutableStateOf(listOf<ChatMessageUiModel>()) }
  val keyboardController = LocalSoftwareKeyboardController.current

//  LaunchedEffect(key1 = uiState.chatMessageList.size, key2 = isKeyboardOpen) {
//    if (uiState.chatMessageList.isNotEmpty()) {
//      listState.scrollToItem(uiState.chatMessageList.size - 1)
//    }
//  }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Column(
      modifier
        .fillMaxSize()
        .noRippleClickable {
          keyboardController?.hide()
        },
    ) {
      Spacer(modifier = Modifier.height(16.dp))
      ChatTopBar(
        modifier = Modifier.height(56.dp),
        onNavigateBack = onNavigateBack,
        navigateToResult = checkEmotionIsJudged,
        isEndChat = uiState.isEndChat,
      )
      HorizontalDivider(color = Gray500)
      Spacer(modifier = Modifier.height(8.dp))
      if (uiState.isLoading) {
        LoadingScreen(modifier = Modifier.fillMaxSize())
      }
//      LazyColumn(
//        modifier = Modifier.weight(1f),
//        state = listState,
//      ) {
//        item {
//          Row(
//            modifier
//              .fillMaxWidth()
//              .wrapContentHeight(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//          ) {
//            Text(
//              text = getCurrentTime().formatDate(),
//              style = TextMRegular,
//              color = Gray500,
//            )
//          }
//          Spacer(modifier = Modifier.height(8.dp))
//          Row(
//            modifier
//              .fillMaxWidth()
//              .wrapContentHeight(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//          ) {
//            Text(
//              text = stringResource(R.string.start_chat_info),
//              style = TextXsRegular,
//              color = Gray500,
//              textAlign = TextAlign.Center,
//            )
//          }
//          Spacer(modifier = Modifier.height(8.dp))
//        }
//        items(
//          items = uiState.chatMessageList,
//          key = { it.message + " " + it.timestamp + Random.nextInt() },
//        ) { chatMessage ->
//          ChatBubble(chatMessage = chatMessage)
//        }
//      }
      uiState.chatMessageList?.let {
        LazyColumn(
          modifier = Modifier.weight(1f),
          state = listState,
        ) {
          item {
            Row(
              modifier
                .fillMaxWidth()
                .wrapContentHeight(),
              horizontalArrangement = Arrangement.Center,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                text = getCurrentTime().formatDate(),
                style = TextMRegular,
                color = Gray500,
              )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
              modifier
                .fillMaxWidth()
                .wrapContentHeight(),
              horizontalArrangement = Arrangement.Center,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                text = stringResource(R.string.start_chat_info),
                style = TextXsRegular,
                color = Gray500,
                textAlign = TextAlign.Center,
              )
            }
            Spacer(modifier = Modifier.height(8.dp))
          }
          items(
            items = uiState.chatMessageList,
            key = { it.message + " " + it.timestamp + Random.nextInt() },
          ) { chatMessage ->
            ChatBubble(chatMessage = chatMessage)
          }
          if (isKeyboardOpen || previousChat.size != it.size) {
            if (it.isNotEmpty()) {
              scope.launch {
                listState.scrollToItem(it.size - 1)
              }
            }
          }
        }
        previousChat = it
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .imePadding()
          .padding(start = 16.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        OutlinedTextField(
          modifier = Modifier
            .weight(1f)
            .heightIn(min = 56.dp, max = 84.dp),
          value = uiState.chatInputMessage,
          singleLine = false,
          onValueChange = updateChatInputMessage,
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
          ),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Gray900,
            unfocusedBorderColor = Gray900,
            cursorColor = Gray900,
            focusedTextColor = Gray900,
            selectionColors = TextSelectionColors(handleColor = Gray500, backgroundColor = Gray500),
          ),
        )
        IconButton(
          onClick = {
            if (uiState.chatInputMessage.isNotEmpty()) sendChatMessage()
          },
          enabled = !uiState.isLoading,
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Outlined.Send,
            contentDescription = stringResource(R.string.send_message_description),
          )
        }
      }
    }
  }
}
