package com.spavv.m.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.FakeData
import com.spavv.m.data.dataSources.CategoryDataSource
import com.spavv.m.data.dataSources.GetCategoriesQuery
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import kotlinx.coroutines.launch
import java.util.Date

class HomeVM(private val productDataSource: ProductDataSource, private val categoryDataSource: CategoryDataSource): ViewModel() {
    private val _specialProduct = mutableStateOf<Paginate<Product>>(Paginate<Product>(listOf(), 1, 10, 100, 10))
    val specialProduct: State<Paginate<Product>> = _specialProduct
    private fun updateSpecialProducts(products: Paginate<Product>) {
        _specialProduct.value = products;
    }

    private val _categories = mutableStateOf<Paginate<Category>>(Paginate<Category>(listOf(), 1, 10, 100, 10))
    val categories: State<Paginate<Category>> = _categories
    private fun updateCategories(categories: Paginate<Category>) {
        _categories.value = categories
    }

    init {
        fetchProducts(
            GetProductsQuery(
                page = 1,
                size = 10,
                sortBy = "CreatedAt",
                isAsc = true,
                filterBy = "Name"
            )
        )
        fetchCategories()
    }

    fun fetchProducts(query: GetProductsQuery) {
        viewModelScope.launch {
            try {
                val products = productDataSource.getProducts(query)
                if(products != null){
                    updateSpecialProducts(products);
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val categories = categoryDataSource.getCategories(GetCategoriesQuery(page = 1, size = 10))
                   if(categories != null){
                       updateCategories(categories)
                   }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}