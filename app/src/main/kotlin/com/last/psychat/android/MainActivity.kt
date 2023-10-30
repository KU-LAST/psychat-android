package com.last.psychat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.last.psychat.android.ui.PsyChatApp
import com.last.pyschat.android.core.designsystem.theme.PsychatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PsychatTheme {
        PsyChatApp()
      }
    }
  }
}
