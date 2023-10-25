package com.last.psychat.android.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
internal fun ChatRoute(
  navigateToResult: (NavOptions) -> Unit
) {
  ChatScreen(navigateToResult = navigateToResult)
}

@Composable
internal fun ChatScreen(
  modifier: Modifier = Modifier,
  navigateToResult: (NavOptions) -> Unit
) {
  val context = LocalContext.current
  Surface(
    modifier = modifier.fillMaxSize(),
  ) {
    Box {
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
