package com.fernandocejas.ktor.trinity

import com.fernandocejas.ktor.trinity.core.extension.empty
import io.ktor.application.*

class Environment(appEnvironment: ApplicationEnvironment) {

    private val mode = when (getConfigProperty(appEnvironment, DEVELOPMENT_PROPERTY).toBoolean()) {
        true -> Mode.DEVELOPMENT
        false -> Mode.PRODUCTION
    }

    val sslPort = when (System.getenv(ENV_PORT_SSL)) {
        null, empty() -> getConfigProperty(appEnvironment, SSL_PORT_PROPERTY).toInt()
        else -> System.getenv(ENV_PORT_SSL).toInt()
    }

    fun hello() = HELLO
    fun about() = mode.name

    private fun getConfigProperty(environment: ApplicationEnvironment, property: String) =
        environment.config.propertyOrNull(property)?.getString() ?: empty()

    companion object {
        const val HELLO = "Hello, World!"

        const val SSL_PORT_PROPERTY = "ktor.deployment.sslPort"
        const val DEVELOPMENT_PROPERTY = "ktor.development"

        const val ENV_PORT_SSL = "ENV_PORT_SSL"
    }

    enum class Mode { DEVELOPMENT, PRODUCTION }
}