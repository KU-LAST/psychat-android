package com.last.psychat.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.last.psychat.android.feature.chat.navigation.chatScreen
import com.last.psychat.android.feature.chat.navigation.navigateToChat
import com.last.psychat.android.feature.main.navigation.MAIN_NAVIGATION_ROUTE
import com.last.psychat.android.feature.main.navigation.mainScreen
import com.last.psychat.android.feature.main.navigation.navigateToMain
import com.last.psychat.android.feature.result.navigation.navigateToResult
import com.last.psychat.android.feature.result.navigation.resultScreen
import com.last.psychat.android.feature.splash.navigation.splashScreen
import com.last.psychat.android.ui.PsyChatAppState

@Composable
fun PsyChatNavHost(
  modifier: Modifier = Modifier,
  appState: PsyChatAppState,
) {
  val navController = appState.navController

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = MAIN_NAVIGATION_ROUTE,
  ) {
    splashScreen(
      navigateToMain = navController::navigateToMain
    )

    mainScreen(
      navigateToChat = { sessionId, isEndChat ->
        navController.navigateToChat(sessionId = sessionId, isEndChat = isEndChat)
      },
    )

    chatScreen(
      onNavigateBack = navController::popBackStack,
      navigateToResult = { emotion ->
        navController.navigateToResult(emotion = emotion)
      },
    )

    resultScreen(
      navigateToMain = navController::navigateToMain,
    )
  }
}
