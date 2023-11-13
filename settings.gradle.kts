@file:Suppress("UnstableApiUsage")

rootProject.name = "psychat-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("build-logic")
}

buildCache {
  local {
    removeUnusedEntriesAfterDays = 7
  }
}

include(
  ":app",
  ":core:data",
  ":core:designsystem",
  ":core:domain",
  ":core:ui",
  ":core:util",
  ":feature:main",
  ":feature:chat",
  ":feature:result",
  ":feature:splash",
)
