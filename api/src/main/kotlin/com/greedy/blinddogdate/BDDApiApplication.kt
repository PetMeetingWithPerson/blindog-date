package com.greedy.blinddogdate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication(exclude = [WebMvcAutoConfiguration::class])
@EnableWebFlux
class BDDApiApplication

fun main(args: Array<String>) {
    runApplication<BDDApiApplication>(*args)
}