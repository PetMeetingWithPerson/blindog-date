package com.greedy.blinddogdate.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.r2dbc")
data class R2dbcProperties(
    val master: DataSource,
) {
    data class DataSource(
        val r2dbcUrl: String,
        val username: String,
        val password: String,
    )
}
