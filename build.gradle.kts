plugins {
    // Application Specific Plugins
    id("org.jetbrains.kotlin.jvm")
    id("application")

    // Internal Script plugins
    id(ScriptPlugins.compilation)
    id(ScriptPlugins.infrastructure)
}

repositories {
    jcenter()
    mavenCentral()
}

group = "com.fernandocejas.restapi"
version = "1.0-SNAPSHOT"
application { mainClass.set("io.ktor.server.netty.EngineMain") }

dependencies {
    // Application dependencies
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:1.5.2")
    implementation("io.ktor:ktor-server-netty:1.5.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    // Unit/Android tests dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
    testImplementation("io.ktor:ktor-server-tests:1.5.2")
}