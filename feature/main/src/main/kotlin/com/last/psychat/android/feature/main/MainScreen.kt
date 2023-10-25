package com.last.psychat.android.feature.main

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
import com.last.psychat.android.feature.main.navigation.MAIN_NAVIGATION_ROUTE

@Composable
internal fun MainRoute(
  navigateToChat: (NavOptions) -> Unit,
) {
  MainScreen(navigateToChat = navigateToChat)
}

@Composable
internal fun MainScreen(
  modifier: Modifier = Modifier,
  navigateToChat: (NavOptions) -> Unit,
) {
  val context = LocalContext.current

  Surface(
    modifier = modifier.fillMaxSize(),
  ) {
    Box {
      PsyChatButton(
        onClick = {
          val options = NavOptions.Builder()
            .setPopUpTo(MAIN_NAVIGATION_ROUTE, inclusive = true)
            .build()
          navigateToChat(options)
        },
        text = context.getString(R.string.start_chat),
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 32.dp),
      )
    }
  }
}
