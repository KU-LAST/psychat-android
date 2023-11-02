package com.last.psychat.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberPsyChatAppState(
  navController: NavHostController = rememberNavController(),
): PsyChatAppState {
  return remember(key1 = navController) {
    PsyChatAppState(navController = navController)
  }
}

@Stable
class PsyChatAppState(
  val navController: NavHostController,
)
