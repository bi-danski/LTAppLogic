package com.lifetrack.model.data

data class SaltedHash(
    val hash: String,
    val salt: String
)

data class TokenClaim(
    val name: String,
    val value: String
)

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expiresIn: Long,
    val secret: String
)