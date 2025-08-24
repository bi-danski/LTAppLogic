package com.lifetrack.model.data.users

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document
import java.time.ZonedDateTime

@Serializable
data class Hospital(
    val hospitalId: String,
    val hospitalName: String,
    val hospitalLocation: String,
    @Contextual
    val lastUpdated: ZonedDateTime,
) {
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}