package core

import java.security.SecureRandom

fun generateLifeTrackId(): String {
    return SecureRandom().toString()

}