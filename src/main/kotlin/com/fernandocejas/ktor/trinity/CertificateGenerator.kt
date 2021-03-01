package com.fernandocejas.ktor.trinity

import io.ktor.network.tls.certificates.generateCertificate
import java.io.File

/**
 * TODO: This is a temporary certificate for SSL
 * @see: https://ktor.io/docs/self-signed-certificate.html
 */
object CertificateGenerator {
    @JvmStatic
    fun main(args: Array<String>) {
        val jksFile = File("build/temporary.jks").apply { parentFile.mkdirs() }
        if (!jksFile.exists()) {
            generateCertificate(jksFile)
        }
    }
}