package com.lifetrack.model.data.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class Kiongozi(
    var uuid: String = "",
    val fullName: String,
    val emailAddress: String,
    val lifetrackID : String = "",
    var passwordHash: String = "",
    var phoneNumber: String = ""
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}