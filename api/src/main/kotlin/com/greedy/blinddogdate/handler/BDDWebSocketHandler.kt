package com.greedy.blinddogdate.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * WebSocket Handler.
 * Spring Security 적용 이후 인증 로직 추가 필요
 * */
@Component
class BDDWebSocketHandler: WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        return Mono.`when`(
            session.receive()
                .map { session.textMessage("hello ${it.payloadAsText}") }
                .then(),
            session.send(
                Flux.interval(Duration.ofSeconds(1))
                    .map { session.textMessage("Hello! World") }
            )
        ).then()
    }
}