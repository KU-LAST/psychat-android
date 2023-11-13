package com.last.psychat.android.feature.splash

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.feature.splash.navigation.SPLASH_NAVIGATION_ROUTE


@Composable
internal fun SplashRoute(
  navigateToMain: (NavOptions) -> Unit,
  viewModel: SplashViewModel = hiltViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  ObserveAsEvents(viewModel.eventFlow) { event ->
    when (event) {
      is SplashUiEvent.NavigateToMain -> {
        val options = NavOptions.Builder()
          .setPopUpTo(SPLASH_NAVIGATION_ROUTE, inclusive = true)
          .build()
        navigateToMain(options)
      }
      is SplashUiEvent.ShowToast -> {
        Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
      }
    }
  }
}

@Composable
fun SplashScreen(
  modifier: Modifier = Modifier,
) {

}