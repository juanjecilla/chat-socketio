package com.luzia.chat.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.luzia.chat.R
import com.luzia.chat.ui.ChatItem
import com.luzia.chat.ui.utils.MockData

@Composable
fun ChatScreen(chatViewModel: ChatViewModel = hiltViewModel()) {
    val state by chatViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        chatViewModel.connect()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (state) {
                ChatState.Error -> {
                    ChatScreen_Error()
                }

                ChatState.Loading -> {
                    ChatScreen_Loading()
                }

                is ChatState.Success -> {
                    ChatScreen_Content(
                        state = state as ChatState.Success,
                        onMessageSent = { message ->
                            chatViewModel.sendMessage(message)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatScreen_Content(
    state: ChatState.Success,
    modifier: Modifier = Modifier,
    onMessageSent: (String) -> Unit = {},
) {

    var inputMessage by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(state.messages) { chatItem ->
                ChatItem(chatItem = chatItem, modifier = Modifier.fillMaxSize())
            }
        }

        TextField(
            value = inputMessage,
            onValueChange = { inputMessage = it },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    onMessageSent(inputMessage)
                    inputMessage = ""
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = stringResource(R.string.send_message)
                    )
                }
            }
        )
    }
}

@Composable
fun ChatItem(chatItem: ChatItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier, horizontalArrangement = if (chatItem.isSentByMe) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = chatItem.userId)
            Text(text = chatItem.message)
        }
    }
}

@Composable
private fun ChatScreen_Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun ChatScreen_Error(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = stringResource(R.string.error_loading_data),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ChatScreen_Content_Preview() {
    ChatScreen_Content(state = ChatState.Success(MockData.messages))
}
