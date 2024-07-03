package com.greedy.blinddogdate.controller

import com.greedy.blinddogdate.r2dbc.entity.SampleEntity
import com.greedy.blinddogdate.r2dbc.service.SampleService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/sample")
class SampleController(
    val sampleService: SampleService
) {
    @RequestMapping("/{id}")
    suspend fun getSample(@PathVariable id: Long): SampleEntity {
        return sampleService.getOrThrow(id)
    }
}