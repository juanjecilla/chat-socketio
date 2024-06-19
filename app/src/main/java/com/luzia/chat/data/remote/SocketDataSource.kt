package com.luzia.chat.data.remote

import android.util.Log
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SocketDataSource @Inject constructor(
    private val socket: Socket,
    private val coroutineScope: CoroutineScope
) {

    private val _messages = MutableSharedFlow<List<String>>()
    val messages = _messages.asSharedFlow().onEach { Log.d("HOLA", it.toString()) }

    fun connect() {
        coroutineScope.launch {
            socket.connect()


            Log.d("HOLA", socket.connected().toString())

            socket.on(Socket.EVENT_CONNECT) {
                Log.d("HOLA1", it.toString())
            }

            socket.on("chat message"){
                val message = it.first().toString()
                coroutineScope.launch {
                    _messages.emit(listOf(message))
                }
            }
        }
    }

    fun sendMessage(message: String) {
        coroutineScope.launch {
            socket.send(message)
        }
    }
}