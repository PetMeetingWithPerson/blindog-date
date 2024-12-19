package com.greedy.blinddogdate.mongo.table

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collation = "message_header_table")
data class MessageHeaderTable(
    @Id
    val id: Long?,
    val from: Long,
    val to: Long,
    val content: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    constructor(from: Long, to: Long) : this(
        id = null,
        from = from,
        to = to,
        content = null,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )
}
