package com.spavv.m.ui.screens.product

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.data.dataSources.BrandDataSource
import com.spavv.m.data.dataSources.CartDataSource
import com.spavv.m.data.dataSources.CategoryDataSource
import com.spavv.m.data.dataSources.GetBrandsQuery
import com.spavv.m.data.dataSources.GetCategoriesQuery
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.data.models.payload.AddToCartRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductVM(
    private val productDataSource: ProductDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val brandDataSource: BrandDataSource,
    private val cartDataSource: CartDataSource
) : ViewModel() {

    var isLoading = mutableStateOf<Boolean>(false);

    private val _products = mutableStateOf<Paginate<Product>?>(null)
    val products: State<Paginate<Product>?> = _products

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    private val _categories = mutableStateOf<Paginate<Category>?>(null)
    val categories: State<Paginate<Category>?> = _categories

    private val _category = mutableStateOf<Category?>(null);

    private val _brands = mutableStateOf<Paginate<Brand>?>(null)
    val brands: State<Paginate<Brand>?> = _brands

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

    private fun updateBrands(brands: Paginate<Brand>?) {
        _brands.value = brands
    }

    val getProductsQuery: MutableState<GetProductsQuery> = mutableStateOf(
        GetProductsQuery(
            page = 1,
            size = 6,
            isAsc = true,
            sortBy = "Price",
            category = "",
            brand = "",
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

    val getBrandsQuery: MutableState<GetBrandsQuery> = mutableStateOf(
        GetBrandsQuery(
            page = 1,
            size = 10
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

    fun fetchBrands() {
        viewModelScope.launch {
            try {
                val brands = brandDataSource.getBrands(getBrandsQuery.value)
                if (brands != null) {
                    updateBrands(brands)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addToCart(request: AddToCartRequest, showToast: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = cartDataSource.addToCart(request)
                showToast("Add to cart successfully!")
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Fail to add to cart!")
            }
        }
    }
}