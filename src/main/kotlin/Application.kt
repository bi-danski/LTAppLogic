package com.lifetrack

import com.lifetrack.plugins.configureMonitoring
import com.lifetrack.plugins.configureRouting
import com.lifetrack.plugins.configureSecurity
import com.lifetrack.plugins.configureSerialization
import com.lifetrack.plugins.connectToMongoDB
import com.lifetrack.services.UserService
import com.mongodb.MongoClientException
import com.mongodb.client.MongoClient
import io.ktor.client.plugins.logging.Logging
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    try {
        val mongoConn = connectToMongoDB()
        val userService = UserService(mongoConn)
        configureRouting(userService)

    } catch (exc: Exception) {
        log.error(exc.toString())
    } catch (err: MongoClientException) {
        log.error(err.stackTrace.toString())
    }
    configureSerialization()
    configureSecurity()
    configureMonitoring()
}
