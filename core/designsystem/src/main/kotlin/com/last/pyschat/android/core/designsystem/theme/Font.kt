package com.last.pyschat.android.core.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.last.psychat.android.core.designsystem.R

// Fonts
val pretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal))
val pretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal))
val pretendardSemiBold = FontFamily(Font(R.font.pretendard_semi_bold, FontWeight.SemiBold, FontStyle.Normal))

// Styles
val H1 = TextStyle(
  fontFamily = pretendardBold,
  fontWeight = FontWeight.Bold,
  fontSize = 34.sp,
  lineHeight = 51.sp,
)

val H2 = TextStyle(
  fontFamily = pretendardBold,
  fontWeight = FontWeight.Bold,
  fontSize = 30.sp,
  lineHeight = 45.sp,
)

val H3 = TextStyle(
  fontFamily = pretendardBold,
  fontWeight = FontWeight.Bold,
  fontSize = 25.sp,
  lineHeight = 37.5.sp,
)

val H4 = TextStyle(
  fontFamily = pretendardBold,
  fontWeight = FontWeight.Bold,
  fontSize = 24.sp,
  lineHeight = 36.sp,
)

val H5 = TextStyle(
  fontFamily = pretendardSemiBold,
  fontSize = 20.sp,
  lineHeight = 30.sp,
)

val Title = TextStyle(
  fontFamily = pretendardSemiBold,
  fontSize = 18.sp,
  lineHeight = 27.sp,
  textAlign = TextAlign.Center,
)

val TextLSemiBold = TextStyle(
  fontFamily = pretendardSemiBold,
  fontSize = 16.sp,
  lineHeight = 24.sp,
)

val TextLMedium = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 16.sp,
  lineHeight = 24.sp,
)

val TextLRegular = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 16.sp,
  lineHeight = 24.sp,
)

val TextMSemiBold = TextStyle(
  fontFamily = pretendardSemiBold,
  fontSize = 15.sp,
  lineHeight = 22.5.sp,
)

val TextMMedium = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 15.sp,
  lineHeight = 22.5.sp,
)

val TextMRegular = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 15.sp,
  lineHeight = 22.5.sp,
)

val TextSSemiBold = TextStyle(
  fontFamily = pretendardSemiBold,
  fontSize = 14.sp,
  lineHeight = 21.sp,
)

val TextSMedium = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 14.sp,
  lineHeight = 21.sp,
)

val TextSRegular = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 14.sp,
  lineHeight = 21.sp,
)

val TextXsRegular = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 13.sp,
  lineHeight = 19.5.sp,
)

val InfoS = TextStyle(
  fontFamily = pretendardMedium,
  fontSize = 10.sp,
  textAlign = TextAlign.Center,
)
