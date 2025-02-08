package com.spavv.m.ui.screens.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductVM(private val productDataSource: ProductDataSource) : ViewModel() {

    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    init {
        fetchProducts(GetProductsQuery())
    }

    fun updateProducts(products: List<Product>) {
        _products.value = products;
    }

    fun updateProduct(product: Product) {
        _product.value = product;
    }

    fun fetchProducts(query: GetProductsQuery) {
        viewModelScope.launch {
            try {
                val products = productDataSource.getProducts(query)
                updateProducts(products);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchProduct(id: String) {
        viewModelScope.launch {
            try {
                val product = productDataSource.getProduct(id)
                if (product != null) {
                    updateProduct(product)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}