package com.spavv.m.data.api

import com.spavv.m.data.models.Category
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import com.spavv.m.data.models.base.BaseResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SkinTestApi {
    @GET("/skin-tests")
    suspend fun getSkinTests(@Header("Authorization") token : String): Response<BaseResult<List<SkinTestQuestion>>>

    @GET("/skin-tests/{id}")
    suspend fun getSkinTest(@Header("Authorization") token : String, @Path("id") id: String): Response<BaseResult<SkinTestQuestion>>

    @POST("/skin-tests/result")
    suspend fun submitSkinTest(@Header("Authorization") token : String, @Body answers: SubmitSkinTestRequest): Response<BaseResult<SkinType>>
}

data class SubmitSkinTestRequest(
    val chosenOptions: Map<String, SkinTestOption>
)