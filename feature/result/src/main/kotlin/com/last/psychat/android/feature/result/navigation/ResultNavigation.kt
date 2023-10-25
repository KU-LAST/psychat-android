package com.last.psychat.android.feature.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.last.psychat.android.feature.result.ResultRoute

const val RESULT_NAVIGATION_ROUTE = "result_route"

fun NavController.navigateToResult(navOptions: NavOptions? = null) {
  this.navigate(RESULT_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.resultScreen(navigateToMain: (NavOptions) -> Unit) {
  composable(route = RESULT_NAVIGATION_ROUTE) {
    ResultRoute(navigateToMain)
  }
}
