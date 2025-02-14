package com.spavv.m.ui.screens.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.data.dataSources.CategoryDataSource
import com.spavv.m.data.dataSources.GetCategoriesQuery
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductVM(
    private val productDataSource: ProductDataSource,
    private val categoryDataSource: CategoryDataSource
) : ViewModel() {

    var isLoading = mutableStateOf<Boolean>(false);

    private val _products = mutableStateOf<Paginate<Product>?>(null)
    val products: State<Paginate<Product>?> = _products

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    private val _categories = mutableStateOf<Paginate<Category>?>(null)
    val categories: State<Paginate<Category>?> = _categories

    private val _category = mutableStateOf<Category?>(null);

    private fun updateProducts(products: Paginate<Product>?) {
        _products.value = products;
    }

    private fun updateProduct(product: Product) {
        _product.value = product;
    }

    private fun updateCategories(categories: Paginate<Category>?) {
        _categories.value = categories
    }

    private fun updateCategory(category: Category?) {
        _category.value = category
    }

    val getProductsQuery: MutableState<GetProductsQuery> = mutableStateOf(
        GetProductsQuery(
            page = 1,
            size = 10,
            isAsc = true,
            sortBy = "Price",
            category = "",
            filterBy = "Name",
            filterQuery = ""
        )
    )

    val getCategoriesQuery: MutableState<GetCategoriesQuery> = mutableStateOf(
        GetCategoriesQuery(
            page = 1,
            size = 10,
        )
    )

    fun updateQuery(newQuery: GetProductsQuery) {
        getProductsQuery.value = newQuery
    }

    fun fetchProducts() {
        isLoading.value = true;
        viewModelScope.launch {
            try {
                val products = productDataSource.getProducts(getProductsQuery.value)
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

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val categories = categoryDataSource.getCategories(getCategoriesQuery.value)
                if (categories != null) {
                    updateCategories(categories)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}