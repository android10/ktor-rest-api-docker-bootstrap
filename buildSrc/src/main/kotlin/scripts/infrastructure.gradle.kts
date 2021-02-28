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

tasks.named<Wrapper>("wrapper") {
    gradleVersion = BuildPlugins.Versions.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("runUnitTests") {
    description = "Runs all Unit Tests."
    dependsOn(":test")
}

tasks.register("runDev", Exec::class) {
    description = "Runs App in Development Mode."
    dependsOn(":run")
}

tasks.register("runInContainer", Exec::class) {
    description = "Runs App in Production Mode inside a Docker Container."
    dependsOn(":run")
}

tasks.register("deployToHeroku", Exec::class) {
    description = "Deploys a containerized Prod App to Heroku."
    dependsOn(":run")
}