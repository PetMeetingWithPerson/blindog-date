package com.greedy.blinddogdate.mongo.service

import com.greedy.blinddogdate.mongo.repository.MessageHeaderTableRepository
import com.greedy.blinddogdate.mongo.table.MessageHeaderTable
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class MessageHeaderMongoService(
    val messageHeaderTableRepository: MessageHeaderTableRepository,
) {
    suspend fun getOrThrow(id: Long): MessageHeaderTable =
        messageHeaderTableRepository.findById(id)
            ?: throw IllegalArgumentException("MessageHeaderTable not found. id=$id")

    suspend fun save(messageHeaderTable: MessageHeaderTable) = messageHeaderTableRepository.save(messageHeaderTable)

    suspend fun getHeadersByUserId(userId: Long) = messageHeaderTableRepository.findByFromOrToOrderByUpdatedAtDesc(userId, userId)

    suspend fun hasChatBefore(
        from: Long,
        to: Long,
    ): MessageHeaderTable? =
        (
            messageHeaderTableRepository
                .findByFromAndTo(from, to)
                .toList() +
                messageHeaderTableRepository
                    .findByFromAndTo(to, from)
                    .toList()
        ).firstOrNull()
}
