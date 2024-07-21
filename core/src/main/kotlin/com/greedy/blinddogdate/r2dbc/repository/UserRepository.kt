package com.greedy.blinddogdate.r2dbc.repository

import com.greedy.blinddogdate.r2dbc.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CoroutineCrudRepository<UserEntity, Long>