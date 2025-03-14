package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.CategoryApi
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.exceptions.UnauthorizedException

data class GetCategoriesQuery(
    var page: Int = 1,
    var size: Int = 10,
)

interface CategoryDataSource {
    suspend fun getCategories(query: GetCategoriesQuery): Paginate<Category>?;
    suspend fun getCategory(id: String): Category?;
}

class CategoryDataSourceImpl(
    private val categoryApi: CategoryApi,
    private val sharedPreferences: SharedPreferences
    ) : CategoryDataSource {
    override suspend fun getCategories(query: GetCategoriesQuery): Paginate<Category>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = categoryApi.getCategories(
                token,
                query.page,
                query.size,
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

    override suspend fun getCategory(id: String): Category? {
        TODO("Not yet implemented")
    }

}