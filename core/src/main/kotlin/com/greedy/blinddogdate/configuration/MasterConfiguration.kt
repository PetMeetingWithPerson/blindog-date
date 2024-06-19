package com.greedy.blinddogdate.configuration

import com.greedy.blinddogdate.properties.R2dbcProperties
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.TransactionManager

@Configuration
@EnableR2dbcRepositories(
    basePackages = ["com.greedy.blinddogdate.r2dbc.repository"],
    entityOperationsRef = "master2dbcEntityTemplate",
)
class MasterConfiguration(
    val r2dbcProperties: R2dbcProperties,
) {

    @Primary
    @Bean
    fun masterConnectionFactory(): ConnectionFactory {
        return ConnectionFactoryBuilder.withUrl(r2dbcProperties.master.r2dbcUrl)
            .username("root")
            .password("root")
            .build()


//        return ConnectionFactories.get(
//            parse(r2dbcProperties.master.r2dbcUrl)
////            parse("r2dbc:pool:mariadb://127.0.0.1:3306/blind_dog_date")
//                .mutate()
////                .option(DRIVER, "pool")
////                .option(DATABASE, "mysql")
//                .option(USER, r2dbcProperties.master.username)
//                .option(PASSWORD, r2dbcProperties.master.password)
//                .build(),
//        )
    }

    @Primary
    @Bean
    fun master2dbcEntityTemplate(
        @Qualifier("masterConnectionFactory") connectionFactory: ConnectionFactory,
    ): R2dbcEntityOperations {
        return R2dbcEntityTemplate(connectionFactory)
    }

    @Primary
    @Bean
    fun masterR2dbcTransactionManager(
        @Qualifier("masterConnectionFactory") connectionFactory: ConnectionFactory,
    ): TransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

//    @Primary
//    @Bean
//    fun masterDSLContext(@Qualifier("masterConnectionFactory") connectionFactory: ConnectionFactory): DSLContext {
//        return DSL.using(connectionFactory, SQLDialect.MYSQL).dsl()
//    }
}
