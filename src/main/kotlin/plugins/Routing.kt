package com.lifetrack.plugins

import core.generateLifeTrackId
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.lifetrack.model.network.HttpClientFactory
import com.lifetrack.model.data.User
import com.lifetrack.services.CohereApiService
import com.lifetrack.services.UserService
import io.ktor.client.engine.cio.CIO


fun Application.configureRouting(userService: UserService) {
    val cohereService = CohereApiService()
    val httpClientEngine = HttpClientFactory().create(CIO.create())

    routing {
        get("/") {
            call.respondText("LifeTrack Ktor Back-End running. Powered by Bidanski\n")
        }

        post("/users"){
            call.respond(userService.collection.toString())
            log.info(userService.collection.toString())
            val user = User(
                emailAddress = "bidanski@hotmail.com",
                lifetrackId = generateLifeTrackId(),
                phoneNumber = "254790938365"
            )
            try {
                userService.create(user)
                call.respond("User added successfully")
            }catch(exc: Exception){
                log.error(exc.toString())
            }
////            this@configureRouting
        }
        post("/cohere"){
            cohereService.generateApiResponse(
                httpClientEngine,
                call = call
            )

        }

//        routing {
//            post("/users") {
//                val user = call.receive<User>()
//                val id = carService.create(user)
//                call.respond(HttpStatusCode.Created, id)
//            }
//            get("/users/{id}") {
//                val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
//                carService.read(id)?.let { car ->
//                    call.respond(car)
//                } ?: call.respond(HttpStatusCode.NotFound)
//            }
//            put("/users/{id}") {
//                val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
//                val car = call.receive<User>()
//                carService.update(id, car)?.let {
//                    call.respond(HttpStatusCode.OK)
//                } ?: call.respond(HttpStatusCode.NotFound)
//            }
//            delete("/users/{id}") {
//                val id = call.parameters["id"] ?: throw IllegalArgumentException("No ID found")
//                carService.delete(id)?.let {
//                    call.respond(HttpStatusCode.OK)
//                } ?: call.respond(HttpStatusCode.NotFound)
//            }
//        }
    }
}
