package com.fernandocejas.ktor.trinity

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val appEnvironment = Environment(environment)

//    install(HttpsRedirect) { sslPort = appEnvironment.sslPort }
    install(HttpsRedirect) { sslPort = 8443 }
    route(appEnvironment)
}

private fun Application.route(appEnvironment: Environment) {
    routing {
        get("/") {
            call.respondText(appEnvironment.hello())
        }

        get("/about") {
            call.respondText(appEnvironment.about())
        }
    }
}
