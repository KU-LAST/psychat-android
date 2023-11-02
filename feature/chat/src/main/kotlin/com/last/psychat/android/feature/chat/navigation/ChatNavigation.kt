package com.last.psychat.android.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.last.psychat.android.feature.chat.ChatRoute

const val CHAT_SESSION_ID = "session_id"
const val CHAT_NAVIGATION_ROUTE = "chat_route/{$CHAT_SESSION_ID}"

fun NavController.navigateToChat(
  navOptions: NavOptions? = null,
  sessionId: Long,
) {
  this.navigate("chat_route/$sessionId", navOptions)
}

fun NavGraphBuilder.chatScreen(
  onNavigateBack: () -> Unit,
  navigateToResult: (NavOptions) -> Unit,
) {
  composable(
    route = CHAT_NAVIGATION_ROUTE,
    arguments = listOf(
      navArgument(CHAT_SESSION_ID) {
        type = NavType.LongType
      },
    ),
  ) {
    ChatRoute(
      onNavigateBack = onNavigateBack,
      navigateToResult = navigateToResult,
    )
  }
}
