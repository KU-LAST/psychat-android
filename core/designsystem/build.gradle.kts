@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-library")
  psychat("android-compose")
}

android {
  namespace = "com.last.psychat.android.core.designsystem"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementations(
    libs.androidx.core,
    libs.bundles.androidx.compose,
  )
}
