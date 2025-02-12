package com.spavv.m.data.dataSources

import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.api.SkinTypeApi
import com.spavv.m.data.api.SubmitSkinTestRequest
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SkinTypeDataSource {
    suspend fun getSkinTypes(): List<SkinType>;
}

class SkinTypeDataSourceImp(private val skinTypeApi: SkinTypeApi) : SkinTypeDataSource {

    override suspend fun getSkinTypes(): List<SkinType> {
        try {
            val response = skinTypeApi.getSkinTypes()


            if (response.body()?.status == 200) {
                return response.body()?.data ?: emptyList()
            }
            return emptyList();

        } catch (e: Exception) {
            return emptyList()
        }
    }

}

