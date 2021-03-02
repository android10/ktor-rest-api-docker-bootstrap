object Dependencies {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10"
    const val shadowFatJarPlugin = "com.github.jengelman.gradle.plugins:shadow:6.1.0"
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