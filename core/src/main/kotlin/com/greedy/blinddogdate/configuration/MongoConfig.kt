package com.greedy.blinddogdate.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(
    basePackages = ["com.greedy.blinddogdate.mongo.repository"],
)
class MongoConfig