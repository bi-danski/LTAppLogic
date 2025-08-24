package com.lifetrack.utils.hashing

import com.lifetrack.model.data.token.SaltedHash

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}