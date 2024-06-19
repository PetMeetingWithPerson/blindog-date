package com.greedy.blinddogdate.application

import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import com.greedy.blinddogdate.r2dbc.service.SampleService
import org.springframework.stereotype.Service

@Service
class SampleApplication(
    val sampleService: SampleService
) {
    suspend fun get(id: Long): SampleEntity {
        return sampleService.getOrThrow(id)
    }
}