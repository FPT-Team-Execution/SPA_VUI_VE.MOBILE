package com.spavv.m.ui.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.LocalNavigation
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.screens.ScaffoldLayout

//import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun CartScreen(modifier: Modifier) {

    val navController = LocalNavigation.current;
    val cartVM = viewModel<CartVM>(
        factory = viewModelFactory {
            CartVM(
                MyApp.appModule.cartDataSource
            )
        }
    )

    LaunchedEffect(Unit) {
        cartVM.fetchCart()
    }

    ScaffoldLayout(navController) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            cartVM.cart.value?.forEach { it ->
                Text(text = it.product.name)
            }

        }
    }

}