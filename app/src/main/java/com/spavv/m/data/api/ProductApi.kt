package com.spavv.m.data.api

import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
        @Query("category") category: String? = null,
        @Query("filterBy") filterBy: String? = null,
        @Query("filterQuery") filterQuery: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("isAsc") isAsc: Boolean = true
    ): Response<BaseResult<List<Product>>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): Response<BaseResult<Product>>
}

