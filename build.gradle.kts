plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.0"
    id("application")
}

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath (BuildPlugins.kotlinGradlePlugin)
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core:1.5.2")
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation ("io.ktor:ktor-serialization:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")

    testImplementation("io.ktor:ktor-server-tests:1.4.0")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    group.set("com.fernando.restapi")
    version = "1.0-SNAPSHOT"
    mainClass.set("com.fernandocejas.restapi.ApplicationKt")
}