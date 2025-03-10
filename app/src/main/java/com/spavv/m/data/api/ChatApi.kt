package com.spavv.m.data.api

import com.spavv.m.data.models.Category
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {
    @GET("bot/chat")
    suspend fun chat(
        @Query("prompt") prompt: String,
    ): Response<BaseResult<String>>

}