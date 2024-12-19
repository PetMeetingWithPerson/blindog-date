package com.greedy.blinddogdate.mongo.table

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collation = "message_table")
data class MessageTable(
    @Id
    val id: String?,
    val messageHeaderId: Long,
    val message: String,
    val from: Boolean,
    val read: Boolean,
    val createdAt: LocalDateTime?,
){
    constructor(messageHeaderId: Long, message: String, from: Boolean) : this(
        id = null,
        messageHeaderId = messageHeaderId,
        message = message,
        from = from,
        read = false,
        createdAt = LocalDateTime.now())
}