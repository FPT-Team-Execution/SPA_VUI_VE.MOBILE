package com.spavv.m.data.dataSources

import Promotion
import com.spavv.m.data.api.PromotionApi

interface PromotionDataSource {
    suspend fun getPromotions(): List<Promotion>?;
}

class PromotionDataSourceImpl(private val promotionApi: PromotionApi) : PromotionDataSource{
    override suspend fun getPromotions(): List<Promotion>? {
        try {

            val response = promotionApi.getPromotions()

            if (response.body()?.status == 200) {
                return response.body()?.data;
            }
            return emptyList()
        } catch (e: Exception) {
            return emptyList()
        }
    }

}