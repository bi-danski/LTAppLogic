package com.lifetrack.model.data

import kotlinx.serialization.Serializable

@Serializable
data class CohereRequest(val model: String = "command-r",
                         val prompt: String,
                         val maxTokens: Int = 300
)

@Serializable
data class CohereResponse(val generations: List<Generation>)

@Serializable
data class Generation(val text: String)

@Serializable
class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null
)