package com.spavv.m.data.api

import com.spavv.m.data.models.Item
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface CartApi {

    @GET("cart")
    suspend fun getCart(
    ): Response<List<Item>>

    @POST("cart")
    suspend fun addToCart(
    ): Response<List<Item>>

    @PATCH("cart")
    suspend fun updateToCart(
    ): Response<List<Item>>

    @DELETE("cart")
    suspend fun removeFromCart(
    ): Response<List<Item>>
}
