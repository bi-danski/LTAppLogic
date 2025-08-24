package com.lifetrack.utils

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun generateLifeTrackId(): String {
    return Uuid.random().toString()
}
