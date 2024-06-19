package com.greedy.blinddogdate.configuration

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@Configuration
@EnableR2dbcAuditing
@ConfigurationPropertiesScan(basePackages = ["com.greedy.blinddogdate.properties"])
class CoreConfiguration
