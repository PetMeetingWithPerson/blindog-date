package com.greedy.blinddogdate.application

import com.greedy.blinddogdate.controller.request.MessageInitRequest
import com.greedy.blinddogdate.controller.response.MessageInitResponse
import com.greedy.blinddogdate.mongo.service.MessageHeaderMongoService
import com.greedy.blinddogdate.mongo.service.MessageMongoService
import com.greedy.blinddogdate.mongo.table.MessageHeaderTable
import com.greedy.blinddogdate.mongo.table.MessageTable
import org.aspectj.bridge.MessageHandler
import org.springframework.stereotype.Service

@Service
class MessageApplication(
    val messageMongoService: MessageMongoService,
    val messageHeaderMongoService: MessageHeaderMongoService,
) {
    suspend fun init(request: MessageInitRequest): MessageInitResponse {
        val from = request.from
        val to = request.to
        val header = messageHeaderMongoService.hasChatBefore(from, to)
        return header?.let { MessageInitResponse(it.id!!, from, to) }
            ?: createMessageHeader(from, to)
    }

    suspend fun load(messageHeaderId: Long) = messageMongoService.load(messageHeaderId)

    suspend fun getHeaders(userId: Long) = messageHeaderMongoService.getHeadersByUserId(userId)

    suspend fun createMessageHeader(
        from: Long,
        to: Long,
    ): MessageInitResponse {
        val messageHeader = MessageHeaderTable(from = from, to = to)
        return MessageInitResponse(messageHeader.id, messageHeader.from, messageHeader.to)
    }

    suspend fun send(
        me: Long,
        headerId: Long,
        content: String,
    ) {
        val messageHeader = messageHeaderMongoService.getOrThrow(headerId)
        val doISend = messageHeader.from == me

        val message =
            MessageTable(
                messageHeaderId = headerId,
                message = content,
                from = doISend,
            ).let { messageMongoService.save(it) }
    }

    suspend fun createSampleMessageHeader(from: Long, to: Long): MessageHeaderTable {
        val messageHeader = MessageHeaderTable(from, to)
        return messageHeaderMongoService.save(messageHeader)
    }
}
