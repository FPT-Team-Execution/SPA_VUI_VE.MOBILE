package com.spavv.m.data.dataSources

import android.util.Log
import com.spavv.m.data.api.ProductApi
import com.spavv.m.data.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class GetProductsQuery(
    var page: Int = 1,
    var size: Int = 10,
    var category: String? = null,
    var filterBy: String? = null,
    var filterQuery: String? = null,
    var sortBy: String? = null,
    var isAsc: Boolean = true
)


interface ProductDataSource {
    suspend fun getProducts(query: GetProductsQuery): List<Product>;
    suspend fun getProduct(id: String): Product?;
}

class ProductDataSourceImpl(private val productApi: ProductApi) : ProductDataSource {

    override suspend fun getProducts(query: GetProductsQuery): List<Product> {
        try {
            val response = withContext(Dispatchers.IO) {

            }
            val demo = productApi.getProducts(
                query.page,
                query.size,
                query.category,
                query.filterQuery,
                query.filterBy,
                query.sortBy,
                query.isAsc
            )

//            if (response.body()?.status == 200) {
//                return response.body()?.data ?: emptyList()
//            }
            return emptyList();

        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList();
        }
    }

    override suspend fun getProduct(id: String): Product? {
        try {
            val response = withContext(Dispatchers.IO) {
                productApi.getProduct(id)
            }
            if (response.body()?.status == 200) {
                return response.body()?.data;
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}