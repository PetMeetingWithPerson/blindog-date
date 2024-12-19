package com.greedy.blinddogdate.controller.request

data class MessageRequest(
    val message: String,
    val sender: Long,
    val receiver: Long,
)
