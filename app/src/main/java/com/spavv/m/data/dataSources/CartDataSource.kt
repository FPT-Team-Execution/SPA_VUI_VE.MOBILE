package com.spavv.m.data.dataSources

import android.content.SharedPreferences
import com.spavv.m.data.api.CartApi
import com.spavv.m.data.models.Item
import com.spavv.m.data.models.base.BaseResult
import com.spavv.m.data.models.payload.AddToCartRequest
import com.spavv.m.exceptions.UnauthorizedException

interface CartDataSource {
    suspend fun getCart(): List<Item>?
    suspend fun addToCart(request: AddToCartRequest): BaseResult<String>?
    suspend fun updateFromCart(request: AddToCartRequest): BaseResult<String>?
    suspend fun removeFromCart(id: String): BaseResult<String>?
}

class CartDataSourceImpl(
    private val cartApi: CartApi,
    private val sharedPreferences: SharedPreferences
) : CartDataSource {

    override suspend fun getCart(): List<Item>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = cartApi.getCart(token)
            if (response.code() == 401) {
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

    override suspend fun addToCart(request: AddToCartRequest): BaseResult<String>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = cartApi.addToCart(token, request)
            if (response.code() == 401) {
                throw UnauthorizedException("UnAuthorized")
            }
            if (response.body()?.status == 200) {
                return response.body()!!;
            }
            return null
        } catch (e: UnauthorizedException) {
            throw e   //throw exception to ui layer for using LocalNavigation
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun updateFromCart(request: AddToCartRequest): BaseResult<String>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = cartApi.updateToCart(token, request)
            if (response.code() == 401) {
                throw UnauthorizedException("UnAuthorized")
            }
            if (response.body()?.status == 200) {
                return response.body()!!;
            }
            return null
        } catch (e: UnauthorizedException) {
            throw e   //throw exception to ui layer for using LocalNavigation
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun removeFromCart(id: String): BaseResult<String>? {
        try {
            val token = sharedPreferences.getString("tokenString", "");
            if (token.isNullOrEmpty())
                throw UnauthorizedException("Token is missing")
            val response = cartApi.removeFromCart(token, id)
            if (response.code() == 401) {
                throw UnauthorizedException("UnAuthorized")
            }
            if (response.body()?.status == 200) {
                return response.body()!!;
            }
            return null
        } catch (e: UnauthorizedException) {
            throw e   //throw exception to ui layer for using LocalNavigation
        } catch (e: Exception) {
            return null
        }
    }
}