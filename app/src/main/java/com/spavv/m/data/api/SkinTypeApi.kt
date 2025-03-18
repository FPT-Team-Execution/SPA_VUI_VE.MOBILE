package com.spavv.m.data.api

import com.spavv.m.data.models.SkinType
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SkinTypeApi {
    @GET("/skin-types")
    suspend fun getSkinTypes(@Header("Authorization") token : String): Response<BaseResult<List<SkinType>>>

}