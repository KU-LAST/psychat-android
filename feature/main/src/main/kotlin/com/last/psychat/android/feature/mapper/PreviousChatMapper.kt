package com.last.psychat.android.feature.mapper

import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.feature.model.PreviousChat

internal fun PreviousChatEntity.toUiModel() =
  PreviousChat(
    sessionId = sessionId,
    startDate = startDate,
    emotion = emotion,
    emotionIndex = when (emotion.split("/").first()) {
      "분노" -> 0
      "기쁨" -> 1
      "불안" -> 2
      "당황" -> 3
      "슬픔" -> 4
      "상처" -> 5
      else -> -1
    },
  )
