package com.greedy.blinddogdate.mongo.repository

import com.greedy.blinddogdate.mongo.table.MessageTable
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageTableRepository : CoroutineCrudRepository<MessageTable, String> {
    fun findByMessageHeaderIdOrderByCreatedAtAsc(messageHeaderId: Long): Flow<MessageTable>
}
