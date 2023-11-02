package com.last.psychat.android.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.last.psychat.android.feature.main.MainRoute

const val MAIN_NAVIGATION_ROUTE = "main_route"

fun NavController.navigateToMain(navOptions: NavOptions? = null) {
  this.navigate(MAIN_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.mainScreen(
  navigateToChat: (Long) -> Unit,
) {
  composable(route = MAIN_NAVIGATION_ROUTE) {
    MainRoute(navigateToChat)
  }
}
