package com.fernandocejas.restapi

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        get("/") {
            call.respondText("ktor-trinity: Hello, World!")
        }

        get("/about") {
            val environment = when (environment.config.propertyOrNull("ktor.development")?.getString().toBoolean()) {
                true -> "DEV MODE"
                false -> "PROD MODE"
            }
            call.respondText(environment)
        }
    }
}
