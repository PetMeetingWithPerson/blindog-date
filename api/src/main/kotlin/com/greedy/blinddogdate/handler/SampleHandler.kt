package com.greedy.blinddogdate.handler

import com.greedy.blinddogdate.application.SampleApplication
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Service
class SampleHandler(
    val sampleApplication: SampleApplication
){
    suspend fun getSample(request: ServerRequest): ServerResponse {
        val campaignId = request.pathVariable("id").toLong()
        val result = sampleApplication.get(campaignId)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}
