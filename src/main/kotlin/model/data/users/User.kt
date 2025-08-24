package com.lifetrack.model.data.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document

@Serializable
data class User(
    val lifetrackId: String?,
    val emailAddress: String,
    val phoneNumber: String,
    val password: String? = "",
    val fullName: String? = "",
    val profileImageUrl: String = "",
    val role: String = "patient",
    var status: String = "active",
    var lastActive: String = "Today",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
//    val _id: String = UUID.randomUUID().toString()
){
    fun toDocument(): Document = Document.parse(Json.encodeToString(this))

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromDocument(document: Document): User = json.decodeFromString(document.toJson())
    }
}
