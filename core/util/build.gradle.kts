plugins {
  psychat("jvm-kotlin")
}

dependencies {
  implementations(
    libs.kotlinx.datetime,
    libs.kotlinx.coroutines.core,
  )
}
