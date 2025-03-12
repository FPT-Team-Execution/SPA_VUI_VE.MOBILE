package com.spavv.m.data.api

import com.spavv.m.data.models.Item
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.payload.AddToCartRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {

    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token : String
    ): Response<BaseResult<List<Item>>>

    @POST("cart")
    suspend fun addToCart(
        @Header("Authorization") token : String,
        @Body request: AddToCartRequest
    ): Response<BaseResult<String>>

    @PATCH("cart")
    suspend fun updateToCart(
        @Header("Authorization") token : String,
        @Body request: AddToCartRequest
    ): Response<BaseResult<String>>

    @DELETE("cart/product/{id}")
    suspend fun removeFromCart(
        @Header("Authorization") token : String,
        @Path("id") id: String
    ): Response<BaseResult<String>>

}
