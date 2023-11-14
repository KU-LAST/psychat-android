package com.last.psychat.android.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.last.psychat.android.feature.chat.ChatRoute

const val SESSION_ID = "session_id"
const val IS_END_CHAT = "is_end_chat"
const val CHAT_NAVIGATION_ROUTE = "chat_route/{$SESSION_ID}/${IS_END_CHAT}"

fun NavController.navigateToChat(
  navOptions: NavOptions? = null,
  sessionId: Long,
  isEndChat: Boolean,
) {
  this.navigate("chat_route/$sessionId/$isEndChat", navOptions)
}

fun NavGraphBuilder.chatScreen(
  onNavigateBack: () -> Unit,
  navigateToResult: (String) -> Unit,
) {
  composable(
    route = CHAT_NAVIGATION_ROUTE,
    arguments = listOf(
      navArgument(SESSION_ID) {
        type = NavType.LongType
      },
      navArgument(IS_END_CHAT) {
        type = NavType.BoolType
      }
    ),
  ) {
    ChatRoute(
      onNavigateBack = onNavigateBack,
      navigateToResult = navigateToResult,
    )
  }
}
