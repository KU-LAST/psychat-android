package com.last.psychat.android.feature.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.last.psychat.android.feature.result.ResultRoute

const val EMOTION = "emotion"
const val RESULT_NAVIGATION_ROUTE = "result_route/{$EMOTION}"

fun NavController.navigateToResult(
  navOptions: NavOptions? = null,
  emotion: String,
) {
  this.navigate("result_route/$emotion", navOptions)
}

fun NavGraphBuilder.resultScreen(
  navigateToMain: (NavOptions) -> Unit,
) {
  composable(
    route = RESULT_NAVIGATION_ROUTE,
    arguments = listOf(
      navArgument(EMOTION) {
        type = NavType.StringType
      },
    ),
  ) {
    ResultRoute(navigateToMain)
  }
}
