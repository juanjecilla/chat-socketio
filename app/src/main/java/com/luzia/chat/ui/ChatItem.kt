package com.luzia.chat.ui

class ChatItem(
    val userId: String,
    val message: String,
    val timestamp: Long,
    val isSentByMe: Boolean,
)