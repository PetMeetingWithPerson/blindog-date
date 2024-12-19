package com.greedy.blinddogdate.controller.response

import java.time.LocalDateTime

data class MessageHeaderResponse(
    val content: String,
    val updatedAt: LocalDateTime,
)
