package com.last.psychat.android.feature.chat.mapper

import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.feature.chat.model.ChatMessage
import kotlinx.datetime.Clock

internal fun ChatResponseEntity.toUiModel() =
  ChatMessage(
    message = responseContent,
    timestamp = Clock.System.now().toString(),
    isUser = false
  )
