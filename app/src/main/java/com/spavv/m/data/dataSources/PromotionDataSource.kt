package com.spavv.m.data.dataSources

import Promotion
import android.content.SharedPreferences
import com.spavv.m.data.api.PromotionApi
import com.spavv.m.exceptions.UnauthorizedException

interface PromotionDataSource {
    suspend fun getPromotions(): List<Promotion>?;
}

class PromotionDataSourceImpl(
    private val promotionApi: PromotionApi,
    private val sharedPreferences: SharedPreferences
) : PromotionDataSource{
    override suspend fun getPromotions(): List<Promotion>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if(token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = promotionApi.getPromotions(token)
            if (response.code()  == 401) {
                throw UnauthorizedException("UnAuthorized")
            }
            if (response.body()?.status == 200) {
                return response.body()?.data;
            }
            return emptyList()
        } catch (e: UnauthorizedException) {
            throw e   //throw exception to ui layer for using LocalNavigation
        } catch (e: Exception) {
            return emptyList()
        }
    }

}