package com.last.psychat.android.core.ui

import androidx.compose.ui.graphics.Color
import com.last.psychat.android.core.designsystem.R
import com.last.pyschat.android.core.designsystem.theme.AngryColor
import com.last.pyschat.android.core.designsystem.theme.DepressedColor
import com.last.pyschat.android.core.designsystem.theme.DisappointedColor
import com.last.pyschat.android.core.designsystem.theme.HappyColor
import com.last.pyschat.android.core.designsystem.theme.SurprisedColor
import com.last.pyschat.android.core.designsystem.theme.TenseColor

// 감정의 대분류는 총 6개 (분노, 기쁨, 불안, 당황, 슬픔, 상처)
// 분노 <-> Angry
// 기쁨 <-> Happy
// 불안 <-> Tense
// 당황 <-> Surprised
// 슬픔 <-> Depressed
// 상처 <-> Disappointed
enum class Emotion(
  val icon: Int,
  val contentColor: Color,
  val containerColor: Color,
) {
  // 0.분노
  Angry(
    icon = R.drawable.angry,
    contentColor = Color.White,
    containerColor = AngryColor,
  ),
  // 1.기쁨
  Happy(
    icon = R.drawable.humorous,
    contentColor = Color.Black,
    containerColor = HappyColor,
  ),
  // 2.불안
  Tense(
    icon = R.drawable.tense,
    contentColor = Color.Black,
    containerColor = TenseColor,
  ),
  // 3.당황
  Surprised(
    icon = R.drawable.surprised,
    contentColor = Color.Black,
    containerColor = SurprisedColor,
  ),
  // 4.슬픔
  Depressed(
    icon = R.drawable.depressed,
    contentColor = Color.Black,
    containerColor = DepressedColor,
  ),
  // 5.상처
  Disappointed(
    icon = R.drawable.disappointed,
    contentColor = Color.White,
    containerColor = DisappointedColor,
  ),
}
