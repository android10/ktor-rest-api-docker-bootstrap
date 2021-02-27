/**
 * Copyright (C) 2021 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
object Kotlin {
    const val standardLibrary = "1.4.10"
    const val coroutines = "1.3.9"
}

object BuildPlugins {
    object Versions {
        const val gradleVersion = "6.8.3"
    }

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
}

object ScriptPlugins {
    const val infrastructure = "scripts.infrastructure"
    const val compilation = "scripts.compilation"
}

object Libraries {
    private object Versions {
    }

    const val kotlinStdLib             = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
    const val kotlinCoroutines         = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val mockk = "1.10.0"
        const val kluent = "1.14"
    }

    const val junit4          = "junit:junit:${Versions.junit4}"
    const val mockk           = "io.mockk:mockk:${Versions.mockk}"
    const val kluent          = "org.amshove.kluent:kluent:${Versions.kluent}"
}