package com.last.psychat.android.feature.mapper

import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.feature.model.PreviousChat

// TODO 아직 진행 중인 대화일 경우 emotion 이 보이지 않도록
internal fun PreviousChatEntity.toUiModel() =
  PreviousChat(
    sessionId = sessionId,
    startDate = startDate,
    emotion = emotion,
    emotionIndex = if (emotion == "행복") 1 else 5
  )
