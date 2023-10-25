package com.last.psychat.android.feature.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.result.navigation.RESULT_NAVIGATION_ROUTE

@Composable
internal fun ResultRoute(
  navigateToMain: (NavOptions) -> Unit,
) {
  ResultScreen(navigateToMain = navigateToMain)
}

@Composable
internal fun ResultScreen(
  modifier: Modifier = Modifier,
  navigateToMain: (NavOptions) -> Unit,
) {
  val context = LocalContext.current

  Surface(
    modifier = modifier.fillMaxSize(),
  ) {
    Box {
      PsyChatButton(
        onClick = {
          val options = NavOptions.Builder()
            .setPopUpTo(RESULT_NAVIGATION_ROUTE, inclusive = true)
            .build()
          navigateToMain(options)
        },
        text = context.getString(R.string.go_back),
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 32.dp),
      )
    }
  }
}
