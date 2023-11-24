# PsyChat Android
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.0-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2022.3.1%20%28Giraff%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-24-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

정신건강 케어 앱 서비스 PsyChat

내 감정 기록을 한눈에, 필요할 때 간편하게, 내 상황에 알맞게

## Features

|이전 채팅 목록 화면|챗봇 채팅 화면|감정 판단 결과 화면|
|:-----:|:-----:|:-----:|
|<img width="240" src="https://github.com/KU-LAST/psychat-android/assets/51016231/44c32ebe-05bf-4a5f-8d06-5ed8b1a7706a">|<img width="240" src="https://github.com/KU-LAST/psychat-android/assets/51016231/6233b39d-3779-416e-a301-905e80201950">|<img width="240" src="https://github.com/KU-LAST/psychat-android/assets/51016231/b7bc7040-319f-4986-8ee6-ba57d062a193">|

## Article
[Compose Navigation 을 통해 Argument 를 전달할 때 주의 해야할 점](https://velog.io/@mraz3068/Points-to-be-cautious-about-when-passing-arguments-through-Compose-Navigation)

[Jetpack Compose 를 사용하여 채팅 화면 UI 구현하기](https://velog.io/@mraz3068/Jetpack-Compose-Chat-Screen-Implementation)

[Type com.last.psychat.android.feature.main.BuildConfig is defined multiple times](https://velog.io/@mraz3068/Type-BuildConfig-is-defined-multiple-times)

## Development

### Required

- IDE : Android Studio Giraffe
- JDK : Java 17을 실행할 수 있는 JDK
- Kotlin Language : 1.9

### Language

- Kotlin

### Libraries

- AndroidX
  - Activity & Activity Compose
  - Core
  - Lifecycle & ViewModel Compose
  - Navigation
  - DataStore
  - StartUp
  - Splash

- Kotlin Libraries (Coroutine, DateTime, Serialization, Immutable)
- Compose
  - Material3
  - Navigation

- Dagger Hilt
- Retrofit
- Timber
- Coil
- [Compose-Stable-Marker](https://github.com/skydoves/compose-stable-marker)

#### Test & Code analysis

- Ktlint
- Detekt

#### Gradle Dependency

- Gradle Version Catalog

## Architecture
Based on [Google App Architecture](https://developer.android.com/topic/architecture) similar to Clean Architecture

<img width="760" alt="image" src="https://github.com/easyhooon/watcha-assignment/assets/51016231/2837a3b6-32f8-46aa-a102-3fb5b3e3826a">

<img width="760" alt="image" src="https://github.com/easyhooon/watcha-assignment/assets/51016231/b29020a1-69aa-482b-8af4-ddb27122a440">

## Package Structure
```
├── app
│   ├── navigation
│   ├── MainActivity
│   └── Application
├── build-logic
├── buildSrc
├── core
│   ├── data
│   ├── designsystem
│   ├── domain
│   ├── util
│   └── ui
├── feature
│   ├── chat
│   ├── main
│   ├── result
│   └── splash
├── gradle
│   └── libs.versions.toml
└── report
    ├── compose-metrics
    └── compose-reports
```
