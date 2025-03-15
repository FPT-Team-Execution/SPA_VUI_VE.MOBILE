package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.api.SkinTypeApi
import com.spavv.m.data.api.SubmitSkinTestRequest
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import com.spavv.m.exceptions.UnauthorizedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SkinTypeDataSource {
    suspend fun getSkinTypes(): List<SkinType>;
}

class SkinTypeDataSourceImp(
    private val skinTypeApi: SkinTypeApi,
    private val sharedPreferences: SharedPreferences
    ) : SkinTypeDataSource {

    override suspend fun getSkinTypes(): List<SkinType> {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if(token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = skinTypeApi.getSkinTypes(token)
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

}

