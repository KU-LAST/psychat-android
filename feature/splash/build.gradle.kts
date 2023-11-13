@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-library")
  psychat("android-compose")
  psychat("android-hilt")
}

android {
  namespace = "com.last.psychat.android.feature.main"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementations(
    projects.core.designsystem,
    projects.core.domain,
    projects.core.ui,
    projects.core.util,
    libs.androidx.core,
    libs.androidx.hilt.compose.navigation,
    libs.timber,
    libs.bundles.androidx.compose,
    libs.bundles.androidx.lifecycle,
  )
}