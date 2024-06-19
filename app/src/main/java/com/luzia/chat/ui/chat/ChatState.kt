package com.luzia.chat.ui.chat

import com.luzia.chat.ui.ChatItem

sealed class ChatState {
    class Success(val messages: List<ChatItem>) : ChatState()
    data object Error : ChatState()
    data object Loading : ChatState()
}