package com.greedy.blinddogdate.configuration

import com.greedy.blinddogdate.handler.BDDWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping

/**
 * 웹소켓 연결을 위한 CustomHandler 작성.
 * */
@Configuration
class WebSocketConfiguration {
    @Bean
    fun handlerMapping(bDDWebSocketHandler: BDDWebSocketHandler): HandlerMapping =
        SimpleUrlHandlerMapping(mapOf("/ws" to bDDWebSocketHandler), -1)

//    @Bean
//    fun webSocketHandlerAdapter(): WebSocketHandlerAdapter =
//        WebSocketHandlerAdapter(webSocketService())
//
//    @Bean
//    fun webSocketService(): WebSocketService = HandshakeWebSocketService()

}
