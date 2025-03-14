package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.BrandApi
import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.exceptions.UnauthorizedException

data class GetBrandsQuery(
    var page: Int = 1,
    var size: Int = 10,
)

interface BrandDataSource {
    suspend fun getBrands(query: GetBrandsQuery): Paginate<Brand>?
}

class BrandDataSourceImpl(
    private val brandApi: BrandApi,
    private val sharedPreferences: SharedPreferences
) : BrandDataSource {
    override suspend fun getBrands(query: GetBrandsQuery): Paginate<Brand>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = brandApi.getBrands(
                token,
                query.page, query.size
            )

            if (response.body()?.status == 200) {
                return response.body()?.data
            }

            return null;
        } catch (e: Exception) {
            e.printStackTrace()
            return null;
        }
    }

}