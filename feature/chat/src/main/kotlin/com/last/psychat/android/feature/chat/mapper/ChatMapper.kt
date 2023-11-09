package com.last.psychat.android.feature.chat.mapper

import com.last.psychat.android.core.domain.entity.chat.BotMessageEntity
import com.last.psychat.android.core.domain.entity.chat.MessageEntity
import com.last.psychat.android.core.domain.entity.chat.ChatResponseEntity
import com.last.psychat.android.core.domain.entity.chat.UserMessageEntity
import com.last.psychat.android.feature.chat.model.ChatMessageUiModel
import kotlinx.datetime.Clock

internal fun ChatResponseEntity.toUiModel() =
  ChatMessageUiModel(
    message = responseContent,
    timestamp = Clock.System.now().toString(),
    isUser = false,
  )

internal fun MessageEntity.toUiModel(): ChatMessageUiModel {
  return when (this) {
    is UserMessageEntity -> {
      ChatMessageUiModel(
        message = messageContent,
        timestamp = timestamp,
        isUser = true,
      )
    }

    is BotMessageEntity -> {
      ChatMessageUiModel(
        message = messageContent,
        timestamp = timestamp,
        isUser = false,
      )
    }

    else -> error("Unknown ChatMessageEntity type")
  }
}

internal fun UserMessageEntity.toUiModel() =
  ChatMessageUiModel(
    message = messageContent,
    timestamp = timestamp,
    isUser = true,
  )

internal fun BotMessageEntity.toUiModel() =
  ChatMessageUiModel(
    message = messageContent,
    timestamp = timestamp,
    isUser = false,
  )
