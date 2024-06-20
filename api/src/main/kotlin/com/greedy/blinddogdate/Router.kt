package com.greedy.blinddogdate

import com.greedy.blinddogdate.handler.SampleHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.coRouter

@Service
class Router(
    val sampleHandler: SampleHandler
) {
    @Bean
    fun migrationRoutes() = coRouter {
        "/v1/simple".nest {
            GET("/{id}", sampleHandler::getSample)
        }
    }
}