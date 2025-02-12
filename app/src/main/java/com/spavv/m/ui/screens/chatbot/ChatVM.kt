package com.spavv.m.ui.screens.chatbot

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.ChatDataSource
import com.spavv.m.data.models.SkinTestQuestion
import kotlinx.coroutines.launch

class ChatVM(private val chatDataSource: ChatDataSource) : ViewModel() {
    private var _chatMessages = mutableStateOf<List<ChatMessageData>>(emptyList())
    val chatMessages: State<List<ChatMessageData>> = _chatMessages

    fun addMessage(message: ChatMessageData) {
        _chatMessages.value += message
    }

    fun sendMessage(message: String){
        viewModelScope.launch {
            try {
                addMessage(ChatMessageData(message, isBot = false));
                val chatMessageResponse = chatDataSource.chat(message)
                addMessage(ChatMessageData(chatMessageResponse, isBot = true));
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class ChatMessageData(
    val text: String,
    val isBot: Boolean
)
