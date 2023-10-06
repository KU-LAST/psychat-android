@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-library")
}

android {
  namespace = "com.last.psychat.android.core.util"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementations(
    libs.kotlinx.datetime,
    libs.androidx.core,
  )
}
