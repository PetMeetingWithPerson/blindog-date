package com.greedy.blinddogdate.application

import com.greedy.blinddogdate.controller.request.CreateUserRequest
import com.greedy.blinddogdate.controller.response.UserResponse
import com.greedy.blinddogdate.r2dbc.entity.UserEntity
import com.greedy.blinddogdate.r2dbc.service.UserService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserApplication(
    val userService: UserService,
) {
    suspend fun create(request: CreateUserRequest): UserResponse {
        val now = LocalDateTime.now()
        val user =
            UserEntity(
                name = request.name,
                email = request.email,
                createdAt = now,
                updatedAt = now,
            ).let { userService.save(it) }

        return UserResponse(user.id!!, user.email)
    }

    suspend fun get(id: Long): UserResponse {
        val user = userService.getOrThrow(id)
        return UserResponse(user.id!!, user.email)
    }
}
