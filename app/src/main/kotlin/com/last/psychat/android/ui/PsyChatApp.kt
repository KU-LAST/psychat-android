package com.last.psychat.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.last.psychat.android.navigation.PsyChatNavHost

@Composable
fun PsyChatApp(
  appState: PsyChatAppState = rememberPsyChatAppState(),
) {
  Scaffold { innerPadding ->
    PsyChatNavHost(
      modifier = Modifier.padding(innerPadding),
      appState = appState,
    )
  }
}
