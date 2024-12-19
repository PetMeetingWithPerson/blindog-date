package com.greedy.blinddogdate.application

import com.greedy.blinddogdate.mongo.service.MessageMongoService
import com.greedy.blinddogdate.mongo.table.MessageTable
import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import com.greedy.blinddogdate.r2dbc.service.SampleService
import org.springframework.stereotype.Service

@Service
class SampleApplication(
    val sampleService: SampleService,
    val messageMongoService: MessageMongoService
) {
    suspend fun get(id: Long): SampleEntity {
        return sampleService.getOrThrow(id)
    }

    suspend fun getMessage(id: String): MessageTable {
        return messageMongoService.getOrThrow(id)
    }
}