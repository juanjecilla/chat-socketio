package com.luzia.chat.domain

import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(message: String)
    fun observeMessages(): Flow<List<String>>
    fun connect()
}