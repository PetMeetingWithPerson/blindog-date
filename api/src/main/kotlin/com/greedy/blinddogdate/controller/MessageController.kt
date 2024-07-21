package com.greedy.blinddogdate.controller

import com.greedy.blinddogdate.application.MessageApplication
import com.greedy.blinddogdate.controller.request.MessageInitRequest
import com.greedy.blinddogdate.controller.response.MessageInitResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/messages")
class MessageController(
    val messageApplication: MessageApplication,
) {
    @PostMapping("/init")
    suspend fun init(
        @RequestBody request: MessageInitRequest,
    ): MessageInitResponse = messageApplication.init(request)

    @GetMapping("/load")
    suspend fun load(messageHeaderId: Long) = messageApplication.load(messageHeaderId)

    @GetMapping("/headers")
    suspend fun getHeaders(@RequestParam userId: Long) = messageApplication.getHeaders(userId)
}
