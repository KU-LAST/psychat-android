package com.last.psychat.android.feature.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.last.psychat.android.feature.splash.SplashRoute

const val SPLASH_NAVIGATION_ROUTE = "splash_route"

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
  this.navigate(SPLASH_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.splashScreen(
  navigateToMain: (NavOptions) -> Unit,
) {
  composable(route = SPLASH_NAVIGATION_ROUTE) {
    SplashRoute(navigateToMain)
  }
}
