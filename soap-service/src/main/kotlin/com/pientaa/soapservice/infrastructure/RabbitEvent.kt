package com.pientaa.soapservice.infrastructure

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

data class RabbitEvent(
    var eventLoad: String,
    var eventType: String,
    var occurredAt: Instant = LocalDateTime.now().toInstant(ZoneOffset.UTC)
)