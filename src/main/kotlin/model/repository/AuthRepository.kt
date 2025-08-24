package com.lifetrack.model.repository

import com.lifetrack.model.data.auth.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun signUp(email: String, password: String, phoneNumber: String, displayName: String): AuthResult
    suspend fun verifyRole(userId: String): String
    suspend fun getTokenId(): String

}
