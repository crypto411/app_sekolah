
# Aplikasi Raport Sekolah
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html) [![Kotlin](https://img.shields.io/badge/java-1.8-orange.svg)](http://kotlinlang.org) [![Gradle](https://img.shields.io/badge/gradle-4.2.1-%2366DCB8.svg)](https://developer.android.com/studio/releases/gradle-plugin)

# Table Of Contents
- [Introduction](#introduction)
- [Usage](#usage)
- [Demo](#demo)
- [Tech Stack](#tech-stack)
- [Mad Scoreboard](#mad-scoreboard)

## Introduction

An application to manage student scores

## Usage

```
===== LOGIN =====
# Admin
username: admin
password: admin

# Siswa
username: [nis]
password: p[nis]
example: nis = 30523 | username: 30523 or u30523 | password: p30523
==========================================================
```

## Demo

|Login|Home - Admin|
|--|--|
|![](assets/login.png?raw=true)|![](assets/home-admin.png?raw=true)|

|Matapelajaran - Admin|Tambah Matapelajaran - Admin|
|--|--|
|![](assets/mapel-admin.png?raw=true)|![](assets/tambahMapel-admin.png?raw=true)|

|Siswa - Admin|Tambah Siswa - Admin|
|--|--|
|![](assets/siswa-admin.png?raw=true)|![](assets/tambahSiswa-admin.png?raw=true)|

|Nilai Pilih Siswa - Admin|Nilai Pilih Mapel - Admin|Update Nilai - Admin|
|--|--|--|
|![](assets/ubahNilai-1-admin.png?raw=true)|![](assets/ubahNilai-2-admin.png?raw=true)|![](assets/ubahNilai-3-admin.png?raw=true)|

## Tech Stack

- [RxJava](https://github.com/ReactiveX/RxJava) Handle the stream of data asynchronously that executes sequentially.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
   - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
   - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
   - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
   - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) dependency injection is a technique whereby one object (or static method) supplies the dependencies of another object. A dependency is an object that can be used (a service).
    - [Hilt-android](https://dagger.dev/hilt/) Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [OkHttp](http://square.github.io/okhttp/) An HTTP & HTTP/2 client for Android and Java applications.
- [Gson](https://github.com/google/gson) A Java serialization/deserialization library to convert Java Objects into JSON and back
- [Glide](https://github.com/bumptech/glide) An image loading and caching library for Android focused on smooth scrolling
- [Material Design](https://material.io/develop/android/docs/getting-started) Material is a design system created by Google to help teams build high-quality digital experiences for Android, iOS, Flutter, and the web.

## Mad Scoreboard

<p align="center">
    <img src="assets/jetpack.png"
        style="margin-right: 20px;"
    />
</p>

## License
```

   Copyright © 2021 Fadhlan Hadaina

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```



