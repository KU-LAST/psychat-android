package com.last.psychat.android.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.last.psychat.android.core.ui.R
import com.last.pyschat.android.core.designsystem.theme.Gray400
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.TextLMedium
import com.last.pyschat.android.core.designsystem.theme.TextSMedium
import com.last.pyschat.android.core.designsystem.theme.Title

@Composable
fun NetworkErrorAlertDialog(
  modifier: Modifier = Modifier,
  title: String,
  description: String,
  onConfirmClick: () -> Unit,
) {
  val context = LocalContext.current
  Dialog(onDismissRequest = {}) {
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = Color.White,
    ) {
      Column(
        modifier = modifier
          .fillMaxWidth()
          .padding(top = 24.dp),
      ) {
        Image(
          painter = painterResource(com.last.psychat.android.core.designsystem.R.drawable.ic_circle_cross),
          contentDescription = context.getString(R.string.circle_cross_descrption),
          modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          modifier = Modifier.align(Alignment.CenterHorizontally),
          text = title,
          color = Gray900,
          style = Title,
          textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          modifier = Modifier.align(Alignment.CenterHorizontally),
          text = description,
          color = Gray400,
          style = TextSMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .align(Alignment.CenterHorizontally),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Button(
            modifier = Modifier
              .weight(1f)
              .height(56.dp),
            onClick = onConfirmClick,
            colors = ButtonDefaults.buttonColors(containerColor = Gray900),
          ) {
            Text(
              text = context.getString(R.string.network_error_retry_message),
              color = Color.White,
              style = TextLMedium,
            )
          }
        }
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}