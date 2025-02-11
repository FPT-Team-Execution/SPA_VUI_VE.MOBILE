package com.spavv.m.data.api

import com.spavv.m.data.models.Category
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SkinTestApi {
    @GET("/skin-tests")
    suspend fun getSkinTests(
    ): Response<BaseResult<List<SkinTestQuestion>>>

    @GET("/skin-tests/{id}")
    suspend fun getSkinTest(@Path("id") id: String): Response<BaseResult<SkinTestQuestion>>
}