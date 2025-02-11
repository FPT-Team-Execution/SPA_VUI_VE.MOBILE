package com.spavv.m.data.dataSources

import com.spavv.m.data.api.ProductApi
import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.SkinTestQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SkinTestDataSource {
    suspend fun getSkinTests(): List<SkinTestQuestion>;
    //suspend fun getSkinTest(id: String): Product?;
}

class SkinTestDataSourceImp(private val skinTestApi: SkinTestApi) : SkinTestDataSource {

    override suspend fun getSkinTests(): List<SkinTestQuestion> {
        try {
            val response = withContext(Dispatchers.IO) {
                skinTestApi.getSkinTests()
            }

            if (response.body()?.status == 200) {
                return response.body()?.data ?: emptyList()
            }
            return emptyList();

        } catch (e: Exception) {
            return emptyList()
        }
    }

//    override suspend fun getSkinTest(id: String): SkinTestQuestion? {
//        try {
//            val response = withContext(Dispatchers.IO) {
//                skinTestApi.getProduct(id)
//            }
//            if (response.body()?.status == 200) {
//                return response.body()?.data;
//            }
//            return null
//        } catch (e: Exception) {
//            return null
//        }
//    }
}