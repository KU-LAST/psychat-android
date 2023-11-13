package com.last.psychat.android.feature.mapper

import com.last.psychat.android.core.domain.entity.chat.PreviousChatEntity
import com.last.psychat.android.feature.model.PreviousChat
import com.last.psychat.core.util.getEmotionIndex

internal fun PreviousChatEntity.toUiModel() =
  PreviousChat(
    sessionId = sessionId,
    startDate = startDate,
    emotion = emotion,
    emotionIndex = getEmotionIndex(emotion)
  )
