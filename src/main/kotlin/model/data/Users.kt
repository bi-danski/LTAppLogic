package com.lifetrack.model.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.bson.Document
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.UUID
import kotlin.uuid.Uuid


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
    constructor() : this(
        hospitalId = "",
        lifetrackId = "",
        fullName = "",
        phoneNumber = "",
        emailAddress = "",
        passwordHash = "",
        profileImageUrl = ""
    )
}

data class Patient(
    val id: String,
    val name: String,
    val age: Int,
    val gender: String,
    val bloodPressure: String,
    val lastVisit: String,
    val condition: String
)

//@JvmOverloads
data class Kiongozi(
    var uuid: String = "",
    val fullName: String,
    val emailAddress: String,
    val lifetrackID : String = "",
    var passwordHash: String = "",
    var phoneNumber: String = ""
){
    constructor() : this("","",""
    )
}
data class Hospital(
    val hospitalId: String,
    val hospitalName: String,
    val hospitalLocation: String
) {
    constructor() : this(
        hospitalId = "",
        hospitalName = "",
        hospitalLocation = ""
    )

}

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
)