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