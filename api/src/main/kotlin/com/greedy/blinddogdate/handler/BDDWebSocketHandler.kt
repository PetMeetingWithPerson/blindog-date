package com.greedy.blinddogdate.handler

import com.greedy.blinddogdate.application.MessageApplication
import com.greedy.blinddogdate.logger
import com.greedy.blinddogdate.redis.MessageRedisService
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Redis Pub/Sub 이용해서 메세지 전달하는 WebSocket Handler
 * */
@Component
class BDDWebSocketHandler(
    val messageApplication: MessageApplication,
    val messageRedisService: MessageRedisService,
) : WebSocketHandler {
    val log by logger()

    override fun handle(session: WebSocketSession): Mono<Void> {
        for (header in session.handshakeInfo.headers) {
            log.info("header: ${header.key} : ${header.value.joinToString(",")}")
        }

        val headerId =
            session.handshakeInfo.headers.getOrEmpty("headerId").firstOrNull()?.toLong()
                ?: throw IllegalArgumentException("headerId is required")

        val me = session.handshakeInfo.headers.getOrEmpty("me").firstOrNull()?.toLong()
            ?: throw IllegalArgumentException("me is required")

        val input =
            session
                .receive()
                .filter { message -> message.type == WebSocketMessage.Type.TEXT }
                .flatMap { message ->
                    val content = message.payloadAsText

                    mono {
                        messageApplication.send(me, headerId, content)
                        messageRedisService.send("test", content)
                    }
                }.then()

        val messageSource =
            messageRedisService
                .receive("test")
                .map {
                    session.textMessage(it)
                }

        val output = session.send(Flux.merge(messageSource))

        return Mono.zip(input, output).then()
    }
}
