package com.spavv.m.ui.screens.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spavv.m.data.dataSources.CartDataSource
import com.spavv.m.data.models.Item
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
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




}