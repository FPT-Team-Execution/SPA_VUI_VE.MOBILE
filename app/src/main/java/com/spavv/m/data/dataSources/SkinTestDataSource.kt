package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.api.SubmitSkinTestRequest
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import com.spavv.m.exceptions.UnauthorizedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SkinTestDataSource {
    suspend fun getSkinTests(): List<SkinTestQuestion>;
    suspend fun submitSkinTest(answers: SubmitSkinTestRequest): SkinType?;
}

class SkinTestDataSourceImp(
    private val skinTestApi: SkinTestApi,
    private val sharedPreferences: SharedPreferences
) : SkinTestDataSource {

    override suspend fun getSkinTests(): List<SkinTestQuestion> {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if(token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = skinTestApi.getSkinTests(token)
            if (response.code()  == 401) {
                throw UnauthorizedException("UnAuthorized")
            }

            if (response.body()?.status == 200) {
                return response.body()?.data ?: emptyList()
            }
            return emptyList();
        } catch (e: UnauthorizedException) {
           throw e;
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun submitSkinTest(answers: SubmitSkinTestRequest): SkinType? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if(token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = withContext(Dispatchers.IO) {
                skinTestApi.submitSkinTest(token, answers)
            }
            if (response.code()  == 401) {
                throw UnauthorizedException("UnAuthorized")
            }
            if (response.body()?.status == 200) {
                return response.body()?.data
            }
            return null
        } catch (e: UnauthorizedException) {
            throw e;
        } catch (e: Exception) {
            return null
        }
    }
}

