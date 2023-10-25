package com.last.psychat.android.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.chat.navigation.CHAT_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray50

@Composable
internal fun ChatRoute(
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
) {
  ChatScreen(
    onNavigateBack = onNavigateBack,
    navigateToResult = navigateToResult
  )
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
) {
  val context = LocalContext.current
  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box {
      Column {
        Spacer(modifier = Modifier.height(16.dp))
        ChatTopBar(onNavigateBack = onNavigateBack)
        Box(modifier = Modifier.fillMaxSize()) {
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
