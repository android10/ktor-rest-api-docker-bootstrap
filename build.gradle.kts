plugins {
    // Application Specific Plugins
    id(BuildPlugins.kotlinJvm)
    id(BuildPlugins.application)

    // Internal Script plugins
    id(ScriptPlugins.compilation)
    id(ScriptPlugins.infrastructure)
}

repositories {
    jcenter()
    mavenCentral()
}

group = AppConfiguration.group
version = AppConfiguration.version
application {
    mainClass.set(AppConfiguration.mainClass)
    applicationName = AppConfiguration.name
    applicationDefaultJvmArgs = AppConfiguration.jvmArgs
}

dependencies {
    // Application dependencies
    implementation(Libraries.kotlinStd)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.ktorServerCore)
    implementation(Libraries.ktorServerNetty)
    implementation(Libraries.logback)

    // Test dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
    testImplementation(TestLibraries.serverTests)
}