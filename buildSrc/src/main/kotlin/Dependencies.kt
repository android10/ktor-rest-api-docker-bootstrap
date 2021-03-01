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

object AppConfiguration {
    const val group = "com.fernandocejas.ktor.trinity"
    const val version = "1.0-SNAPSHOT"
    const val mainClass = "io.ktor.server.netty.EngineMain"
    const val name = "ktor-trinity"

    // In the case of 'io.ktor.development' jvm arg, the config
    // value in the 'application.conf' file has precedence.
    val jvmArgs = listOf("-Dio.ktor.development=true")
}

object Kotlin {
    const val std = "1.4.10"
    const val ktor = "1.5.2"
    const val coroutines = "1.3.9"
}

object BuildPlugins {
    object Versions {
        const val gradleVersion = "6.8.3"
    }

    const val application = "application"
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
}

object ScriptPlugins {
    const val infrastructure = "scripts.infrastructure"
    const val compilation = "scripts.compilation"
}

object Libraries {
    object Versions {
        const val logBackVersion = "1.2.3"
    }

    const val kotlinStd          = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.std}"
    const val kotlinCoroutines   = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val ktorServerCore     = "io.ktor:ktor-server-core:${Kotlin.ktor}"
    const val ktorServerNetty    = "io.ktor:ktor-server-netty:${Kotlin.ktor}"
    const val ktorTlsCertificate = "io.ktor:ktor-network-tls-certificates:${Kotlin.ktor}"
    const val logback            = "ch.qos.logback:logback-classic:${Versions.logBackVersion}"
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
    const val serverTests     = "io.ktor:ktor-server-tests:${Kotlin.ktor}"
}