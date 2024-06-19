package com.luzia.chat.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luzia.chat.data.ChatRepositoryImpl
import com.luzia.chat.domain.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<ChatState> =
        MutableStateFlow(ChatState.Success(emptyList()))
    val state: StateFlow<ChatState> = _state.asStateFlow()

    init {
        viewModelScope.launch(coroutineDispatcher) {
            chatRepository.observeMessages().collect { messages ->
                _state.update {
                    ChatState.Success(messages.map {
                        com.luzia.chat.ui.ChatItem(
                            "",
                            it,
                            System.currentTimeMillis(),
                            false
                        )
                    })
                }
            }
        }
    }

    fun connect(){
        viewModelScope.launch(coroutineDispatcher) {
            chatRepository.connect()
        }
    }

    fun sendMessage(message: String){
        viewModelScope.launch(coroutineDispatcher) {
            chatRepository.sendMessage(message)
        }
    }
}
