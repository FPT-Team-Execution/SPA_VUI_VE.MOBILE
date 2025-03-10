package com.spavv.m.data.api

import Promotion
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PromotionApi {
    @GET("api/promotions")
    suspend fun getPromotions(): Response<BaseResult<List<Promotion>>>
}