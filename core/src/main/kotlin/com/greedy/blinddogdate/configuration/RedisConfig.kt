package com.greedy.blinddogdate.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.lettuce.core.ClientOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.web.server.session.HeaderWebSessionIdResolver
import org.springframework.web.server.session.WebSessionIdResolver
import java.time.Duration


@Configuration
class RedisConfig(
    val objectMapper: ObjectMapper,
) {
    @Bean
    fun reactiveRedisConnectionFactory(): LettuceConnectionFactory {
        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = "localhost"
        redisConf.port = 6379
//        redisConf.password = RedisPassword.of(redisProperties.password)
        val clientOptions =
            ClientOptions
                .builder()
                .publishOnScheduler(true)
                .build()
        val clientConfiguration =
            LettuceClientConfiguration
                .builder()
                .commandTimeout(Duration.ofSeconds(5))
                .shutdownTimeout(Duration.ofSeconds(5))
                .clientOptions(clientOptions)
                .build()
        return LettuceConnectionFactory(redisConf, clientConfiguration)
    }

    @Bean
    fun reactiveStringRedisTemplate(reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory) =
        ReactiveStringRedisTemplate(reactiveRedisConnectionFactory)

    private fun <T> reactiveRedisTemplate(
        clazz: Class<T>,
        factory: ReactiveRedisConnectionFactory,
    ): ReactiveRedisTemplate<String, T> {
        val keySerializer = StringRedisSerializer()
        val hashKeySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(objectMapper, clazz)

        val context =
            RedisSerializationContext
                .newSerializationContext<String, T>(keySerializer)
                .hashKey(hashKeySerializer)
                .hashValue(valueSerializer)
                .value(valueSerializer)
                .build()
        return ReactiveRedisTemplate(factory, context)
    }

    @Bean
    fun webSessionIdResolver(): WebSessionIdResolver {
        val sessionIdResolver = HeaderWebSessionIdResolver()
        sessionIdResolver.headerName = "X-AUTH-TOKEN"
        return sessionIdResolver
    }
}
