package com.spavv.m.data.api

import com.spavv.m.data.models.User
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.base.Paginate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUsers(
        @Query("username") username: String? = null,
        @Query("email") email: String? = null,
        @Query("fullName") fullName: String? = null,
        @Query("phoneNumber") phoneNumber: String? = null,
        @Query("customerProfiles") customerProfiles: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("isAsc") isAsc: Boolean = true
    ): Response<BaseResult<Paginate<User>>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): Response<BaseResult<User>>
}