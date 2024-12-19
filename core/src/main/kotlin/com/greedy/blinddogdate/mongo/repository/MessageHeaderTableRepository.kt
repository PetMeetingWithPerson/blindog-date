package com.greedy.blinddogdate.mongo.repository

import com.greedy.blinddogdate.mongo.table.MessageHeaderTable
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageHeaderTableRepository : CoroutineCrudRepository<MessageHeaderTable, Long> {
    fun findByFromAndTo(
        from: Long,
        to: Long,
    ): Flow<MessageHeaderTable>

    fun findByFromOrToOrderByUpdatedAtDesc(
        from: Long,
        to: Long): Flow<MessageHeaderTable>
}