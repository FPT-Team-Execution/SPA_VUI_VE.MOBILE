package com.spavv.m.data.dataSources

import com.spavv.m.data.api.ChatApi

interface ChatDataSource {
    suspend fun chat(prompt: String): String
}

class ChatDataSourceImpl(private val chatApi: ChatApi) : ChatDataSource {
    override suspend fun chat(prompt: String): String {
        val failMsg = "Bot đang bận, không thể trả lời"
        try {
            val response = chatApi.chat(prompt)


            if (response.body()?.status == 200) {
                return response.body()?.data ?: failMsg
            }
            return failMsg;

        } catch (e: Exception) {
            return failMsg
        }
    }
}