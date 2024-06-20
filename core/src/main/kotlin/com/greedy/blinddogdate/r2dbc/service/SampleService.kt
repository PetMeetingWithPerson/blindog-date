package com.greedy.blinddogdate.r2dbc.service

import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import com.greedy.blinddogdate.r2dbc.repository.SampleRepository
import org.springframework.stereotype.Service

@Service
class SampleService(
    val sampleRepository: SampleRepository
) {
    suspend fun getOrThrow(id: Long): SampleEntity {
        return sampleRepository.findById(id)?: throw Exception("Not Found")
    }
}