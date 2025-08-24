package com.lifetrack.model.data.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document
import java.util.UUID

@Serializable
data class Practitioner(
    var uuid: String = UUID.randomUUID().toString(),
    val accessLevel: Int = 0,
    val hospitalId: String,
    val lifetrackId: String,
    val fullName: String = "",
    val phoneNumber: String = "",
    val emailAddress: String = "",
    var passwordHash: String = "",
    val role: String = "practitioner",
    val profileImageUrl: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }
        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}