plugins {
  psychat("jvm-kotlin")
}

dependencies {
  implementations(
    libs.javax.inject,
    libs.kotlinx.coroutines.core,
  )
}
