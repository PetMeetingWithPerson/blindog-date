package com.greedy.blinddogdate.r2dbc.repository

import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository: CoroutineCrudRepository<SampleEntity, Long>