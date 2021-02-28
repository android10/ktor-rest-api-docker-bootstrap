plugins {
    // Application Specific Plugins
    id("org.jetbrains.kotlin.jvm")
    id("application")

    // Internal Script plugins
    id(ScriptPlugins.compilation)
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:1.5.2")
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("io.ktor:ktor-server-tests:1.5.2")
}

group = "com.fernandocejas.restapi"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}