package com.lifetrack.model.data.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class DoctorProfile(
    val id: Int,
    val name: String,
    val specialty: String,
    val status: String,
    val imageRes: Int,
    val experienceYears: Int,
    val availability: String,
    val rating: Float,
    val hospital: String,
    val waitTime: String
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}