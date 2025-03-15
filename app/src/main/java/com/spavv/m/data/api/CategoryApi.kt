package com.spavv.m.data.api

import com.spavv.m.data.models.Category
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.base.Paginate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {
    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization") token : String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): Response<BaseResult<Paginate<Category>>>

    @GET("categories/{id}")
    suspend fun getCategory(
        @Header("Authorization") token : String,
        @Path("id") id: String
    ): Response<BaseResult<Category>>
}