package com.greedy.blinddogdate.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("sample")
data class SampleEntity(
    @Id
    val id: Long,

    val title: String?,

    val content: String?,

    val createdAt: LocalDateTime?,

    val updatedAt: LocalDateTime?,
)