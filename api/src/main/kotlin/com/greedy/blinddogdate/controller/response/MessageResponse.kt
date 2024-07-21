package com.greedy.blinddogdate.controller.response

import java.time.LocalDateTime

data class MessageResponse(
    val id: String,
    val messageHeaderId: Long,
    val message: String,
    val from: Boolean,
    val read: Boolean,
    val createdAt: LocalDateTime,
)
