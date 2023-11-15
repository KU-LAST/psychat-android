@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-application")
  psychat("android-compose")
  psychat("android-hilt")
}

android {
  namespace = "com.last.psychat.android"

  buildFeatures {
    buildConfig = true
  }

  buildTypes {
    getByName("debug") {
      isDebuggable = true
      applicationIdSuffix = ".dev"
      manifestPlaceholders += mapOf(
        "appName" to "@string/app_name_dev"
      )
    }

    getByName("release") {
      isDebuggable = false
      signingConfig = signingConfigs.getByName("debug")
      manifestPlaceholders += mapOf(
        "appName" to "@string/app_name"
      )
    }
  }
}

dependencies {
  coreLibraryDesugaring(libs.desugar.jdk)
  implementations(
    projects.core.data,
    projects.core.designsystem,
    projects.core.domain,
    projects.core.ui,
    projects.core.util,
    projects.feature.main,
    projects.feature.chat,
    projects.feature.result,
    projects.feature.splash,
    libs.androidx.splash,
    libs.androidx.startup,
    libs.androidx.core,
    libs.androidx.splash,
    libs.timber,
    libs.bundles.androidx.compose,
  )
}
