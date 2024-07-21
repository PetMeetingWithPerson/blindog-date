package com.greedy.blinddogdate.controller

import com.greedy.blinddogdate.application.UserApplication
import com.greedy.blinddogdate.controller.request.CreateUserRequest
import com.greedy.blinddogdate.controller.response.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserController(
    val userApplication: UserApplication
) {

    @PostMapping("")
    suspend fun createUser(@RequestBody request: CreateUserRequest): UserResponse {
        return userApplication.create(request)
    }

    @GetMapping("/{id}")
    suspend fun getUser(@PathVariable id: Long): UserResponse {
        return userApplication.get(id)
    }
}