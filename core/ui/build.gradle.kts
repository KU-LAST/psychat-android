@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-library")
  psychat("android-compose")
}

android {
  namespace = "com.last.psychat.android.core.ui"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementations(
    projects.core.designsystem,
    libs.kotlinx.datetime,
    libs.androidx.core,
    libs.lottie.compose,
    libs.bundles.androidx.compose,
  )
}
