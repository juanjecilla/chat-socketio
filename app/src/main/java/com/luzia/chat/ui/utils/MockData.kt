package com.luzia.chat.ui.utils

import com.luzia.chat.ui.ChatItem

object MockData {
    val messages = List(20) {
        ChatItem(
            userId = if (it % 2 == 0) {
                "user 1"
            } else {
                "user 2"
            },
            message = "Dummy message $it",
            timestamp = 1000L * it,
            isSentByMe = it % 2 == 0
        )
    }
}
