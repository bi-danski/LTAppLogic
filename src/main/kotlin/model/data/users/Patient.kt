package com.lifetrack.model.data.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class Patient(
    val id: String,
    val name: String,
    val age: Int,
    val gender: String,
    val bloodPressure: String,
    val lastVisit: String,
    val condition: String
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}