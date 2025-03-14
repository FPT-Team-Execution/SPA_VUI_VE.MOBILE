package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.ProductApi
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.exceptions.UnauthorizedException

data class GetProductsQuery(
    var page: Int = 1,
    var size: Int = 10,
    var category: String? = null,
    val brand: String? = null,
    var filterBy: String? = null,
    var filterQuery: String? = null,
    var sortBy: String? = null,
    var isAsc: Boolean = true
)


interface ProductDataSource {
    suspend fun getProducts(query: GetProductsQuery): Paginate<Product>?;
    suspend fun getProduct(id: String): Product?;
}

class ProductDataSourceImpl(
    private val productApi: ProductApi, private val sharedPreferences: SharedPreferences
) : ProductDataSource {

    override suspend fun getProducts(query: GetProductsQuery): Paginate<Product>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = productApi.getProducts(
                token,
                query.page,
                query.size,
                query.category,
                query.brand,
                query.filterBy,
                query.filterQuery,
                query.sortBy,
                query.isAsc
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

    override suspend fun getProduct(id: String): Product? {


        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty()) {
                throw UnauthorizedException("Token is missing")
            }
            val response = productApi.getProduct(
                token,
                id,
            )

            if (response.body()?.status == 200) {
                return response.body()?.data;
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}