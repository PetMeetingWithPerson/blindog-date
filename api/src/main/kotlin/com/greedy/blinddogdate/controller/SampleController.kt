package com.greedy.blinddogdate.controller

import com.greedy.blinddogdate.application.MessageApplication
import com.greedy.blinddogdate.logger
import com.greedy.blinddogdate.mongo.service.MessageMongoService
import com.greedy.blinddogdate.mongo.table.MessageHeaderTable
import com.greedy.blinddogdate.mongo.table.MessageTable
import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import com.greedy.blinddogdate.r2dbc.service.SampleService
import com.greedy.blinddogdate.redis.MessageRedisService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession

@RestController
@RequestMapping("/v1/sample")
class SampleController(
    val sampleService: SampleService,
    val messageMongoService: MessageMongoService,
    val messageRedisService: MessageRedisService,
    val messageApplication: MessageApplication,
) {
    val log by logger()

    @GetMapping("/{id}")
    suspend fun getSample(
        @PathVariable id: Long,
    ): SampleEntity = sampleService.getOrThrow(id)

    @GetMapping("/message/{id}")
    suspend fun getMessage(
        @PathVariable id: String,
    ): MessageTable = messageMongoService.getOrThrow(id)

    @GetMapping("/test/message/send")
    suspend fun sendMessage(
        @RequestParam channel: String,
        @RequestParam message: String,
    ): Long = messageRedisService.send(channel, message)

    @GetMapping("/test")
    suspend fun getSampleTest(
        session: WebSession,
        exchange: ServerWebExchange,
    ): String {
        log.info("Controller header")
        exchange.request.headers.forEach { key, value ->
            log.info("##1 controller : $key : ${value.joinToString(",")}")
        }

        val sessionId = session.id
        log.info("##3 controller : $sessionId")
        exchange.response.headers.add(HttpHeaders.AUTHORIZATION, "Bearer $sessionId")
        return sessionId
    }

    @PostMapping("/messageHeader")
    suspend fun createMessageHeader(
        @RequestParam from: Long,
        @RequestParam to: Long,
    ): MessageHeaderTable = messageApplication.createSampleMessageHeader(from, to)
}
