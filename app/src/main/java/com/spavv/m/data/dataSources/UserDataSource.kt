package com.spavv.m.data.dataSources

import com.spavv.m.data.api.UserApi
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.base.Paginate
import retrofit2.Response

class UserDataSource(private val userApi: UserApi) {

    suspend fun getUser(
        username: String? = null,
        email: String? = null,
        fullname: String? = null,
        filterQuery: String? = null,
        sortBy: String? = null,
        isAsc: Boolean = true
    ): Response<BaseResult<Paginate<Product>>> {
        return userApi.getUser(
            username = username,
            email = email,
            fullname = fullname,
            filterQuery = filterQuery,
            sortBy = sortBy,
            isAsc = isAsc
        )
    }

    suspend fun getProduct(id: String): Response<BaseResult<Product>> {
        return userApi.getProduct(id)
    }
}