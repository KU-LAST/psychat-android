package com.last.psychat.android.feature.chat.model

data class ChatMessageUiModel(
  val message: String,
  val timestamp: String,
  val isUser: Boolean,
)

//sealed class ChatMessageUiModel {
//  data class UserMessageUiModel(
//    val message: String,
//    val timeStamp: String,
//    val isUser: String,
//  ): ChatMessageUiModel()
//  data class BotMessageUiModel(
//    val response: String,
//    val timeStamp: String,
//    val isUser: String,
//  ): ChatMessageUiModel()
//}