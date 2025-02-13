package com.spavv.m.ui.screens.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductVM(private val productDataSource: ProductDataSource) : ViewModel() {

    var isLoading = mutableStateOf<Boolean>(false);

    private val _products = mutableStateOf<Paginate<Product>?>(null)
    val products: State<Paginate<Product>?> = _products

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    private fun updateProducts(products: Paginate<Product>?) {
        _products.value = products;
    }

    private fun updateProduct(product: Product) {
        _product.value = product;
    }

    val query: MutableState<GetProductsQuery> = mutableStateOf(
        GetProductsQuery(
            page = 1,
            size = 10,
            isAsc = true,
            sortBy = "Price",
            category = "",
            filterBy = "name",
            filterQuery = ""
        )
    )

    fun updateQuery(newQuery: GetProductsQuery) {
        query.value = newQuery
    }

    fun fetchProducts() {
        isLoading.value = true;
        viewModelScope.launch {
            try {
                val products = productDataSource.getProducts(query.value)
                updateProducts(products);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        isLoading.value = false;
    }

    fun fetchProduct(id: String) {
        isLoading.value = true;
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
        isLoading.value = false;
    }
}