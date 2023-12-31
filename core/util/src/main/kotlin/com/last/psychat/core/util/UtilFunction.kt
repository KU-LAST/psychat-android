package com.last.psychat.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentTime(): String {
  val current = LocalDateTime.now()
  val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
  return current.format(formatter)
}

fun getCurrentTimeFormatted(): String {
  val current = LocalDateTime.now()
  val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
  return current.format(formatter)
}

fun getEmotionIndex(emotion: String): Int {
  return when (emotion.split("/").first()) {
    "분노" -> 0
    "기쁨" -> 1
    "불안" -> 2
    "당황" -> 3
    "슬픔" -> 4
    "상처" -> 5
    else -> -1
  }
}
