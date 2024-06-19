package com.luzia.chat.data

import com.luzia.chat.data.remote.SocketDataSource
import com.luzia.chat.domain.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val socketDataSource: SocketDataSource
) : ChatRepository {

    override fun observeMessages(): Flow<List<String>> {
        return socketDataSource.messages
    }

    override suspend fun sendMessage(message: String) {
        socketDataSource.sendMessage(message)
    }

    override fun connect(){
        socketDataSource.connect()
    }
}