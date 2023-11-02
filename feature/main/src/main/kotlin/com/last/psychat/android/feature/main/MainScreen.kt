package com.last.psychat.android.feature.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.last.pyschat.android.core.designsystem.theme.Gray50

@Composable
internal fun MainRoute(
  navigateToChat: (NavOptions) -> Unit,
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
  navigateToChat: (NavOptions) -> Unit,
) {
  val context = LocalContext.current

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      PsyChatButton(
//        onClick = {
//          val options = NavOptions.Builder()
//            .setPopUpTo(MAIN_NAVIGATION_ROUTE, inclusive = true)
//            .build()
//          navigateToChat(options)
//        },
        onClick = startChatSession,
        text = context.getString(R.string.start_chat),
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 32.dp),
      )
    }
  }
}
