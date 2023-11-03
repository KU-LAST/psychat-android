package com.last.psychat.android.feature.mapper

import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.feature.model.PreviousChat

internal fun PreviousChatEntity.toUiModel() =
  PreviousChat(
    sessionId = sessionId,
    startDate = startDate,
    emotion = emotion,
    emotionIndex = if (emotion == "행복") 1 else 5
  )
