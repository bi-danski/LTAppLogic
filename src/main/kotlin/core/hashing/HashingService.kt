package com.lifetrack.core.hashing

import com.lifetrack.model.data.SaltedHash

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}