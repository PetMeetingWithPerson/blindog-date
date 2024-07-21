package com.greedy.blinddogdate.r2dbc.service

import com.greedy.blinddogdate.r2dbc.entity.UserEntity
import com.greedy.blinddogdate.r2dbc.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
) {
    suspend fun getOrThrow(id: Long) = userRepository.findById(id) ?: throw Exception("User not found")

    suspend fun save(user: UserEntity) = userRepository.save(user)
}
