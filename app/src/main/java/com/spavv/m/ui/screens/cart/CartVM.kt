package com.spavv.m.ui.screens.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spavv.m.data.dataSources.CartDataSource
import com.spavv.m.data.models.Item
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.payload.AddToCartRequest
import kotlinx.coroutines.launch

class CartVM(
    private val cartDataSource: CartDataSource
) : ViewModel() {
    var isLoading = mutableStateOf<Boolean>(false);

    private val _cart = mutableStateOf<List<Item>?>(null)
    val cart: State<List<Item>?> = _cart;

    private fun updateCart(cart: List<Item>?) {
        _cart.value = cart
    }

    fun fetchCart() {
        isLoading.value = true;
        viewModelScope.launch {
            try {
                val cart = cartDataSource.getCart();
                updateCart(cart)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateToCart(request: AddToCartRequest) {
        _cart.value = _cart.value?.toMutableList()?.apply {
            val index = indexOfFirst { it.product.productId == request.productId }
            if (index != -1) {
                this[index] =
                    this[index].copy(quantity = request.quantity)
            }
        }
        viewModelScope.launch {
            try {
                val response = cartDataSource.addToCart(request)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeFromCart(id: String) {
        _cart.value = _cart.value?.toMutableList()?.apply {
            val index = indexOfFirst { it.product.productId == id }
            if (index != -1) {
                this.removeAt(index)
            }
        }
        viewModelScope.launch {
            try {
                val response = cartDataSource.removeFromCart(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}