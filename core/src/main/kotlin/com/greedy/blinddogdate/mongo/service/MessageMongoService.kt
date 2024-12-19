package com.greedy.blinddogdate.mongo.service

import com.greedy.blinddogdate.mongo.repository.MessageTableRepository
import com.greedy.blinddogdate.mongo.table.MessageTable
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class MessageMongoService(
    val messageTableRepository: MessageTableRepository,
) {
    suspend fun getOrThrow(id: String): MessageTable =
        messageTableRepository.findById(id) ?: throw IllegalArgumentException("Message not found")

    suspend fun save(messageTable: MessageTable) = messageTableRepository.save(messageTable)
    suspend fun load(messageHeaderId: Long) = messageTableRepository.findByMessageHeaderIdOrderByCreatedAtAsc(messageHeaderId).toList()
}
