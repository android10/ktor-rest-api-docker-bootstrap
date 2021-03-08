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
object Docker {
    const val GROUP = "Docker"

    object Config {
        const val MEMORY = "512M"
        const val CPUS = "1"
        const val HOST_PORT = 80
        const val HOST_SSL_PORT = 443
        const val CONTAINER_PORT = 5000
        const val CONTAINER_SSL_PORT = 8443
        const val ARTIFACT = JarArtifact.BASENAME
    }

    object Commands {
        private const val DELIMITER = " "
        private const val RUN_PARAMS =
            "-m${Config.MEMORY} " +
            "--cpus ${Config.CPUS} -t " +
            "-p ${Config.HOST_PORT}:${Config.CONTAINER_PORT} " +
            "-p ${Config.HOST_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
            "-p ${Config.CONTAINER_SSL_PORT}:${Config.CONTAINER_SSL_PORT} " +
            "--rm ${Config.ARTIFACT}"

        private const val RUN = "docker run --name=${Config.ARTIFACT}"

        const val BUILD = "docker build -t ${Config.ARTIFACT} ."
        const val RUN_ATTACHED = RUN.plus(DELIMITER).plus(RUN_PARAMS)
        const val RUN_DETACHED = RUN.plus(DELIMITER).plus("-d").plus(DELIMITER).plus(RUN_PARAMS)
        const val STOP_CONTAINER = "docker stop ${Config.ARTIFACT}"
        const val LIST_IMAGES = "docker image ls"
        const val LIST_CONTAINERS = "docker ps -a"
        const val REMOVE_IMAGE = "docker image rm -f ${Config.ARTIFACT}"
        const val REMOVE_DANGLING_IMAGES = "docker image prune --filter=dangling=true -f"

        fun buildExec(command: String) = command.split(DELIMITER)
    }
}

tasks.register("dockerBuildImage", Exec::class) {
    group = Docker.GROUP
    description = "Builds a docker image containing the Ktor Application."
    commandLine(Docker.Commands.buildExec(Docker.Commands.BUILD))
    dependsOn("shadowJar")
}

tasks.register("dockerRun", Exec::class) {
    group = Docker.GROUP
    description = "Runs App inside a Docker Container."
    commandLine(Docker.Commands.buildExec(Docker.Commands.RUN_ATTACHED))
}

tasks.register("dockerRunDetached", Exec::class) {
    group = Docker.GROUP
    description = "Runs App inside a Docker Container in detached mode."
    commandLine(Docker.Commands.buildExec(Docker.Commands.RUN_DETACHED))
}

tasks.register("dockerStop", Exec::class) {
    group = Docker.GROUP
    description = "Stop running Container in host."
    commandLine(Docker.Commands.buildExec(Docker.Commands.STOP_CONTAINER))
}

tasks.register("dockerListImages", Exec::class) {
    group = Docker.GROUP
    description = "Lists available docker images in the host machine."
    commandLine(Docker.Commands.buildExec(Docker.Commands.LIST_IMAGES))
}

tasks.register("dockerListContainers", Exec::class) {
    group = Docker.GROUP
    description = "Lists running docker containers in the host machine."
    commandLine(Docker.Commands.buildExec(Docker.Commands.LIST_CONTAINERS))
}

tasks.register("dockerRemoveImage", Exec::class) {
    group = Docker.GROUP
    description = "Removes this application docker image."
    commandLine(Docker.Commands.buildExec(Docker.Commands.REMOVE_IMAGE))
}

tasks.register("dockerRemoveDanglingImages", Exec::class) {
    group = Docker.GROUP
    description = "Removes all the dangling images if any."
    commandLine(Docker.Commands.buildExec(Docker.Commands.REMOVE_DANGLING_IMAGES))
}

// -------------------------------------------------------------------------------------
// Heroku Gradle Logic
// -------------------------------------------------------------------------------------
tasks.register("deployToHeroku", Exec::class) {
    description = "Deploys a containerized Prod App to Heroku."
    dependsOn("build")
}