package com.spavv.m.data.api

import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.base.Paginate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("user")
    suspend fun getUser(
        @Query("username") category: String? = null,
        @Query("email") brand: String? = null,
        @Query("fullname") filterBy: String? = null,
        @Query("") filterQuery: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("isAsc") isAsc: Boolean = true
    ): Response<BaseResult<Paginate<Product>>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): Response<BaseResult<Product>>
}