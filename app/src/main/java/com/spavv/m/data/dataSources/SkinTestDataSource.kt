package com.spavv.m.data.dataSources

import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.api.SubmitSkinTestRequest
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SkinTestDataSource {
    suspend fun getSkinTests(): List<SkinTestQuestion>;
    suspend fun submitSkinTest(answers: SubmitSkinTestRequest): SkinType?;
}

class SkinTestDataSourceImp(private val skinTestApi: SkinTestApi) : SkinTestDataSource {

    override suspend fun getSkinTests(): List<SkinTestQuestion> {
        try {
            val response = skinTestApi.getSkinTests()


            if (response.body()?.status == 200) {
                return response.body()?.data ?: emptyList()
            }
            return emptyList();

        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun submitSkinTest(answers: SubmitSkinTestRequest): SkinType? {
        try {
            val response = withContext(Dispatchers.IO) {
                skinTestApi.submitSkinTest(answers)
            }
            if (response.body()?.status == 200) {
                return response.body()?.data
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}

