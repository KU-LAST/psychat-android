package com.last.psychat.core.util.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// ISO 8601 형식의 날짜와 시간 문자열을 "~년 월 일" 형태로 변환
// "2023-08-02T18:27:25.862Z" -> "~23년 8월 2일"
fun String.toFormatDate(): String {
  val instant = Instant.parse(this)
  val dateTime = instant.toLocalDateTime(TimeZone.UTC)
  return "~${dateTime.year - 2000}년 ${dateTime.monthNumber}월 ${dateTime.dayOfMonth}일"
}

fun String.toStringLocalDateTime(): String {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
  val dueDate = LocalDateTime.parse(this.substring(0, 16), formatter)
  return "${dueDate.year}년 ${dueDate.monthValue}월 ${dueDate.dayOfMonth}일"
}

fun String.toLocalDateTime(): LocalDateTime {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
  return LocalDateTime.parse(this.substring(0, 16), formatter)
}

fun String.toInstant(): Instant {
  return Instant.parse(this)
}

fun String.toKoreanTimeString(): String {
  val current = this.toInstant().toLocalDateTime(TimeZone.currentSystemDefault())
  val hour = current.hour
  val minute = current.minute
  val period = if (hour < 12) "오전" else "오후"
  return "$period ${hour % 12}시 ${minute}분"
}

fun String.formatDate(): String {
  val inputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
  val dateTime = LocalDateTime.parse(this, inputFormatter)
  val outputFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN)
  return dateTime.format(outputFormatter)
}

fun String.formatDateTime(): String {
  val inputFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
  val dateTime = LocalDateTime.parse(this, inputFormatter)
  val outputFormatter = DateTimeFormatter.ofPattern("a h:mm", Locale.KOREAN)
  return dateTime.format(outputFormatter)
}

// fun String.formatDateTime(): String {
//   val instant = Instant.parse(this.replace(" ", "T") + ":00Z") // "2023.11.09 13:38:45" -> "2023-11-09T13:38:45Z"
//   val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
//
//   val hour = if (dateTime.hour > 12) dateTime.hour - 12 else dateTime.hour
//   val period = if (dateTime.hour < 12) "오전" else "오후"
//
//   return "$period ${hour}시 ${dateTime.minute}분 ${dateTime.second}초"
// }
