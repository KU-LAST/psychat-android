package com.last.psychat.android.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.last.psychat.android.feature.chat.ChatRoute

const val CHAT_NAVIGATION_ROUTE = "chat_route"

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
  this.navigate(CHAT_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.chatScreen(navigateToResult: (NavOptions) -> Unit) {
  composable(route = CHAT_NAVIGATION_ROUTE) {
    ChatRoute(navigateToResult)
  }
}
