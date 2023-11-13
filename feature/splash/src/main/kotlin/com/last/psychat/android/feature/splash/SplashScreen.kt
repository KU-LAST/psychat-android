package com.last.psychat.android.feature.splash

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.ObserveAsEvents
import com.last.psychat.android.core.ui.components.AppTitle
import com.last.psychat.android.core.ui.components.LoadingScreen
import com.last.psychat.android.feature.splash.navigation.SPLASH_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray50


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

  SplashScreen(
    uiState = uiState,
    navigateToMain = viewModel::navigateToMain,
  )
}

@Composable
fun SplashScreen(
  modifier: Modifier = Modifier,
  uiState: SplashUiState,
  navigateToMain: () -> Unit,
) {
  when {
    uiState.isLoading -> {
      LoadingScreen(modifier = Modifier.fillMaxSize())
    }

    uiState.isLoggedIn -> {
      navigateToMain()
    }
  }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = Gray50,
  ) {
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      Column(
        modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Image(
          painter = painterResource(com.last.psychat.android.core.designsystem.R.drawable.ic_app),
          contentDescription = "App Icon",
        )
        AppTitle()
      }
    }
  }
}