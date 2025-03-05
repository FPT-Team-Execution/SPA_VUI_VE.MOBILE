package com.spavv.m.data.dataSources

import com.spavv.m.data.api.ProductApi
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate

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
    suspend fun getProducts(query: GetProductsQuery): Paginate<Product>?;
    suspend fun getProduct(id: String): Product?;
}

class ProductDataSourceImpl(private val productApi: ProductApi) : ProductDataSource {

    override suspend fun getProducts(query: GetProductsQuery): Paginate<Product>? {
        try {

            val response = productApi.getProducts(
                query.page,
                query.size,
                query.category,
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

            val response = productApi.getProduct(id)

            if (response.body()?.status == 200) {
                return response.body()?.data;
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}