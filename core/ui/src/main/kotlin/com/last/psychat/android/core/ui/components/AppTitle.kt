package com.last.psychat.android.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.last.psychat.android.core.ui.R
import com.last.pyschat.android.core.designsystem.theme.Gray900
import com.last.pyschat.android.core.designsystem.theme.koronaOneRegular

@Composable
fun AppTitle(
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = stringResource(R.string.psychat),
    color = Gray900,
    fontSize = 24.sp,
    fontWeight = FontWeight.W400,
    fontFamily = koronaOneRegular,
    lineHeight = 20.sp,
    letterSpacing = (-0.36).sp,
  )
}
