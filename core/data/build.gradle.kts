@file:Suppress("INLINE_FROM_HIGHER_PLATFORM", "UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  psychat("android-library")
  psychat("android-hilt")
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.last.psychat.android.core.data"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementations(
    projects.core.domain,
    libs.kotlinx.serialization.json,
    libs.androidx.datastore.preferences,
    libs.bundles.retrofit,
    libs.bundles.okhttp,
    libs.timber,
  )
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + listOf("-opt-in=kotlin.ExperimentalStdlibApi")
  }
}
