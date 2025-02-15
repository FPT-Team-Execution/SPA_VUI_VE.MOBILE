package com.spavv.m.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.FakeData
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Product
import kotlinx.coroutines.launch
import java.util.Date

class HomeVM(private val productDataSource: ProductDataSource): ViewModel() {
    private val _specialProduct = mutableStateOf<List<Product>>(emptyList())
    val specialProduct: State<List<Product>> = _specialProduct
    private fun updateSpecialProducts(products: List<Product>) {
        _specialProduct.value = products;
    }
    init {
        fetchProducts(GetProductsQuery(page = 1, size = 5, sortBy = "CreatedAt", isAsc = false))
    }

    fun fetchProducts(query: GetProductsQuery) {
        viewModelScope.launch {
            try {
                //val products = productDataSource.getProducts(query)
                //* fake data
                val products = FakeData.mockProducts
                updateSpecialProducts(products);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}