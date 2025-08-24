package com.lifetrack.services

import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.*
import com.lifetrack.model.data.CohereRequest
import com.lifetrack.model.data.CohereResponse
import com.lifetrack.model.data.ApiResponse

class CohereApiService {

    suspend fun generateApiResponse(client: HttpClient, call: ApplicationCall) {
        val req = call.receive<CohereRequest>()
        val apiKey = System.getenv("COHERE_S")
        if (apiKey.isNullOrBlank()) {
            call.respond(HttpStatusCode.InternalServerError, ApiResponse<String>(
                success = false,
                error = "Cohere API key not set"
            ))
            return
        }

        try {
            val response: HttpResponse = client.post("https://api.cohere.ai/v1/generate") {
                header("Authorization", "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                setBody(req)
            }.body()

            call.respond(
                ApiResponse(success = true, data = response.body<CohereResponse>().generations.firstOrNull()?.text)
            )

        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, ApiResponse<String>(
                success = false,
                error = e.message
            ))
        }
    }
}