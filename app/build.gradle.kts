@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  psychat("android-application")
  psychat("android-compose")
  psychat("android-hilt")
}

android {
  namespace = "com.last.psychat.android"

  buildTypes {
    getByName("debug") {
      isDebuggable = true
      applicationIdSuffix = ".dev"
      manifestPlaceholders += mapOf(
        "appName" to "@string/app_name_dev",
      )
    }
  }

  buildFeatures {
    buildConfig = true
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
    libs.androidx.splash,
    libs.androidx.startup,
    libs.androidx.core,
    libs.androidx.splash,
    libs.timber,
    libs.bundles.androidx.compose,
  )
}
