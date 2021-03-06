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
package scripts

import scripts.Compilation_gradle.JarArtifact

fun String.Companion.emptySpace() = ""

// -------------------------------------------------------------------------------------
// TODO: This should be removed when properly implementing Certificate Authority
// TODO: providing TLS certificates
// -------------------------------------------------------------------------------------
tasks.register("generateJks", JavaExec::class) {
    description = "Generate self signed SSL certificate."
    classpath =  project.convention.getPlugin(JavaPluginConvention::class)
        .sourceSets.findByName("main")!!.runtimeClasspath
    main = "com.fernandocejas.ktor.trinity.CertificateGenerator"
    dependsOn("classes")
}
getTasksByName("build", false).first().dependsOn("generateJks")
// -------------------------------------------------------------------------------------

tasks.named<Wrapper>("wrapper") {
    gradleVersion = BuildPlugins.Versions.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("runDev", Exec::class) {
    description = "Runs App in Development Mode."
    dependsOn("run")
}

tasks.register("runUnitTests") {
    description = "Runs all Unit Tests."
    dependsOn("test")
}

// -------------------------------------------------------------------------------------
// Docker Gradle Logic
// -------------------------------------------------------------------------------------
object DockerConfig {
    const val MEMORY = "512M"
    const val CPUS = "1"
    const val HOST_PORT = 80
    const val HOST_SSL_PORT = 443
    const val CONTAINER_PORT = 5000
    const val CONTAINER_SSL_PORT = 8443
    const val ARTIFACT = JarArtifact.BASENAME
}

tasks.register("dockerRun", Exec::class) {
    val command = "docker run " +
            "-m${DockerConfig.MEMORY} " +
            "--cpus ${DockerConfig.CPUS} -t " +
            "-p ${DockerConfig.HOST_PORT}:${DockerConfig.CONTAINER_PORT} " +
            "-p ${DockerConfig.HOST_SSL_PORT}:${DockerConfig.CONTAINER_SSL_PORT} " +
            "-p ${DockerConfig.CONTAINER_SSL_PORT}:${DockerConfig.CONTAINER_SSL_PORT} " +
            "--rm ${DockerConfig.ARTIFACT}"

    description = "Runs App in Production Mode inside a Docker Container."
    commandLine(command.split(String.emptySpace()))
}

tasks.register("dockerListImages", Exec::class) {
    description = "List available docker images in the host machine."
    commandLine("docker image ls".split(String.emptySpace()))
}

// -------------------------------------------------------------------------------------
// Heroku Gradle Logic
// -------------------------------------------------------------------------------------
tasks.register("deployToHeroku", Exec::class) {
    description = "Deploys a containerized Prod App to Heroku."
    dependsOn("build")
}