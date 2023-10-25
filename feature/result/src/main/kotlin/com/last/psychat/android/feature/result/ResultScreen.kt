package com.last.psychat.android.feature.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.last.psychat.android.core.ui.components.PsyChatButton
import com.last.psychat.android.feature.result.navigation.RESULT_NAVIGATION_ROUTE
import com.last.pyschat.android.core.designsystem.theme.Gray50
import com.last.pyschat.android.core.designsystem.theme.Gray500
import com.last.pyschat.android.core.designsystem.theme.TextXsRegular

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
    color = Gray50,
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 32.dp),
      contentAlignment = Alignment.BottomCenter // 여기서는 Box의 모든 자식을 중앙에 정렬합니다.
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = stringResource(id = R.string.info_message),
          style = TextXsRegular,
          color = Gray500,
          modifier = Modifier.padding(bottom = 24.dp)
        )
        PsyChatButton(
          onClick = {
            val options = NavOptions.Builder()
              .setPopUpTo(RESULT_NAVIGATION_ROUTE, inclusive = true)
              .build()
            navigateToMain(options)
          },
          text = context.getString(R.string.go_back),
        )
      }
    }
  }
}
