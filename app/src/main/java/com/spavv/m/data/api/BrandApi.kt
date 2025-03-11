package com.spavv.m.data.api

import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.base.Paginate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BrandApi {

    @GET("brands")
    suspend fun getBrands(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ) : Response<BaseResult<Paginate<Brand>>>
}

