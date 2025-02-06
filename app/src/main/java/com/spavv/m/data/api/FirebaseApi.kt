package com.spavv.m.data.api

import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.payload.FirebaseUserNameLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseApi {
    @POST("/firebase-demo-path")
    suspend fun loginByUsername(@Body body: FirebaseUserNameLogin): Response<BaseResult>
}