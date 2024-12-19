package com.greedy.blinddogdate.redis

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MessageRedisService(
    val reactiveStringRedisTemplate: ReactiveStringRedisTemplate,
) {
    suspend fun send(topic: String, message: String): Long {
        return reactiveStringRedisTemplate.convertAndSend(topic, message)
            .awaitSingle()
    }

    fun receive(topic: String): Flux<String> {
        return reactiveStringRedisTemplate.listenToChannel(topic)
            .map { it.message }

    }
}