group = AppConfiguration.group
version = AppConfiguration.version
application {
    mainClass.set(AppConfiguration.mainClass)
    applicationName = AppConfiguration.name
    applicationDefaultJvmArgs = AppConfiguration.jvmArgs
}

plugins {
    // Application Specific Plugins
    id(BuildPlugins.kotlinJvm)
    id(BuildPlugins.application)

    // Internal Script plugins
    id(ScriptPlugins.compilation)
    id(ScriptPlugins.infrastructure)
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("ktor-trinity")
    archiveClassifier.set("fat")
    archiveVersion.set("1.0")
    archiveFileName.set("ktor-trinity-1.0-fat.jar")

    exclude("META-INF/INDEX.LIST")
    exclude("META-INF/*.SF")
    exclude("META-INF/*.DSA")
    exclude("META-INF/*.RSA")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Application dependencies
    implementation(Libraries.kotlinStd)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.ktorServerCore)
    implementation(Libraries.ktorServerNetty)
    implementation(Libraries.ktorTlsCertificate)
    implementation(Libraries.logback)

    // Test dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kluent)
    testImplementation(TestLibraries.serverTests)
}