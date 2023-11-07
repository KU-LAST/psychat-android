package com.last.psychat.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.last.psychat.android.navigation.PsyChatNavHost
import com.last.pyschat.android.core.designsystem.theme.Gray50

@Composable
fun PsyChatApp(
  appState: PsyChatAppState = rememberPsyChatAppState(),
) {
  Scaffold(
    containerColor = Gray50
  ) { innerPadding ->
    PsyChatNavHost(
      modifier = Modifier.padding(innerPadding),
      appState = appState,
    )
  }
}
