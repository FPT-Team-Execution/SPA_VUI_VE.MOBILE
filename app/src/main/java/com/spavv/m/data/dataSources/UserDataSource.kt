package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.UserApi
import com.spavv.m.data.models.User
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.exceptions.UnauthorizedException

data class GetUsersQuery(
    var username: String? = null,
    var email: String? = null,
    var fullName: String? = null,
    var phoneNumber: String? = null,
    var customerProfiles: String? = null,
    var sortBy: String? = null,
    var isAsc: Boolean = true
)

interface UserDataSource {
    suspend fun getUsers(query: GetUsersQuery): Paginate<User>?
    suspend fun getUser(id: String): User?
}

class UserDataSourceImpl(
    private val userApi: UserApi,
    private val sharedPreferences: SharedPreferences
) : UserDataSource {

    override suspend fun getUsers(query: GetUsersQuery): Paginate<User>? {
        try {
            val token = sharedPreferences.getString("tokenString", "")
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = userApi.getUsers(
                username = query.username,
                email = query.email,
                fullName = query.fullName,
                phoneNumber = query.phoneNumber,
                customerProfiles = query.customerProfiles,
                sortBy = query.sortBy,
                isAsc = query.isAsc
            )

            if (response.body()?.status == 200) {
                return response.body()?.data
            }
            return null

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun getUser(id: String): User? {
        try {
            val token = sharedPreferences.getString("tokenString", "")
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = userApi.getUser(id)

            if (response.body()?.status == 200) {
                return response.body()?.data
            }
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}