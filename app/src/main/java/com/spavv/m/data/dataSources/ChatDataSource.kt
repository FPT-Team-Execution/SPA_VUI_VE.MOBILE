package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.ChatApi
import com.spavv.m.exceptions.UnauthorizedException

interface ChatDataSource {
    suspend fun chat(prompt: String): String
}

class ChatDataSourceImpl(
    private val chatApi: ChatApi,
    private val sharedPreferences: SharedPreferences
    ) : ChatDataSource {
    override suspend fun chat(prompt: String): String {
        val failMsg = "Bot đang bận, không thể trả lời"
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if(token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing");
            val response = chatApi.chat(token, prompt)
            if (response.code()  == 401) {
                throw UnauthorizedException("UnAuthorized")
            }

            if (response.body()?.status == 200) {
                return response.body()?.data ?: failMsg
            }
            return failMsg;

        } catch (e: UnauthorizedException) {
            throw e   //throw exception to ui layer for using LocalNavigation
        } catch (e: Exception) {
            return failMsg;
        }
    }
}