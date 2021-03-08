object Dependencies {
    object Versions {
        const val KOTLIN_GRADLE_PLUGIN = "1.4.10"
        const val SHADOW_FAT_JAR_GRADLE_PLUGIN = "6.1.0"
    }

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE_PLUGIN}"
    const val shadowFatJarPlugin = "com.github.jengelman.gradle.plugins:shadow:${Versions.SHADOW_FAT_JAR_GRADLE_PLUGIN}"
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    google()
    mavenCentral()
}

dependencies {
    implementation(Dependencies.kotlinGradlePlugin)
    implementation(Dependencies.shadowFatJarPlugin)
}