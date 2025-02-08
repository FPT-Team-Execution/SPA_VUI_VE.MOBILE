package com.spavv.m.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.api.RetrofitClient
import com.spavv.m.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductVM : ViewModel() {
    private val api = RetrofitClient.api

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                _products.value = api.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchProduct(id: String) {
        viewModelScope.launch {
            try {
                _product.value = api.getProduct(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createProduct(product: Product) {
        viewModelScope.launch {
            try {
                api.createProduct(product)
                fetchProducts() // Reload danh sách
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateProduct(id: String, product: Product) {
        viewModelScope.launch {
            try {
                api.updateProduct(id, product)
                fetchProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            try {
                api.deleteProduct(id)
                fetchProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}