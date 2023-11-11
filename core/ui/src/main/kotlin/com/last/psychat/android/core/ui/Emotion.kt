package com.last.psychat.android.core.ui

import androidx.compose.ui.graphics.Color
import com.last.pyschat.android.core.designsystem.theme.AngryColor
import com.last.pyschat.android.core.designsystem.theme.AwfulColor
import com.last.pyschat.android.core.designsystem.theme.BoredColor
import com.last.pyschat.android.core.designsystem.theme.CalmColor
import com.last.pyschat.android.core.designsystem.theme.DepressedColor
import com.last.pyschat.android.core.designsystem.theme.DisappointedColor
import com.last.pyschat.android.core.designsystem.theme.HappyColor
import com.last.pyschat.android.core.designsystem.theme.HumorousColor
import com.last.pyschat.android.core.designsystem.theme.LonelyColor
import com.last.pyschat.android.core.designsystem.theme.MysteriousColor
import com.last.pyschat.android.core.designsystem.theme.NeutralColor
import com.last.pyschat.android.core.designsystem.theme.RomanticColor
import com.last.pyschat.android.core.designsystem.theme.ShamefulColor
import com.last.pyschat.android.core.designsystem.theme.SurprisedColor
import com.last.pyschat.android.core.designsystem.theme.SuspiciousColor
import com.last.pyschat.android.core.designsystem.theme.TenseColor
import com.last.psychat.android.core.designsystem.R

// TODO 감정 판단 결과와 Emoji 매핑
enum class Emotion(
  val icon: Int,
  val contentColor: Color,
  val containerColor: Color,
) {
  Neutral(
    icon = R.drawable.neutral,
    contentColor = Color.Black,
    containerColor = NeutralColor,
  ),
  Happy(
    icon = R.drawable.happy,
    contentColor = Color.Black,
    containerColor = HappyColor,
  ),
  Angry(
    icon = R.drawable.angry,
    contentColor = Color.White,
    containerColor = AngryColor,
  ),
  Bored(
    icon = R.drawable.bored,
    contentColor = Color.Black,
    containerColor = BoredColor,
  ),
  Calm(
    icon = R.drawable.calm,
    contentColor = Color.Black,
    containerColor = CalmColor,
  ),
  Depressed(
    icon = R.drawable.depressed,
    contentColor = Color.Black,
    containerColor = DepressedColor,
  ),
  Disappointed(
    icon = R.drawable.disappointed,
    contentColor = Color.White,
    containerColor = DisappointedColor,
  ),
  Humorous(
    icon = R.drawable.humorous,
    contentColor = Color.Black,
    containerColor = HumorousColor,
  ),
  Lonely(
    icon = R.drawable.lonely,
    contentColor = Color.White,
    containerColor = LonelyColor,
  ),
  Mysterious(
    icon = R.drawable.mysterious,
    contentColor = Color.Black,
    containerColor = MysteriousColor,
  ),
  Romantic(
    icon = R.drawable.romantic,
    contentColor = Color.White,
    containerColor = RomanticColor,
  ),
  Shameful(
    icon = R.drawable.shameful,
    contentColor = Color.White,
    containerColor = ShamefulColor,
  ),
  Awful(
    icon = R.drawable.awful,
    contentColor = Color.Black,
    containerColor = AwfulColor,
  ),
  Surprised(
    icon = R.drawable.surprised,
    contentColor = Color.Black,
    containerColor = SurprisedColor,
  ),
  Suspicious(
    icon = R.drawable.suspicious,
    contentColor = Color.Black,
    containerColor = SuspiciousColor,
  ),
  Tense(
    icon = R.drawable.tense,
    contentColor = Color.Black,
    containerColor = TenseColor,
  ),
}
