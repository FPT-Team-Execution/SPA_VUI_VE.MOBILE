package com.spavv.m.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.general.Header
import com.spavv.m.ui.components.product.ProductCard
import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun ProductScreen(modifier: Modifier, navController: NavController) {

    val productVM = viewModel<ProductVM>(
        factory = viewModelFactory {
            ProductVM(MyApp.appModule.productDataSource)
        }
    )
    LaunchedEffect(Unit) {
        productVM.fetchProducts(
            GetProductsQuery(
                page = 1,
                size = 10,
                isAsc = true,
                sortBy = "Price",
                category = "",
                filterBy = "Name",
                filterQuery = "La"
            )
        )
    }



    ScaffoldLayout(navController) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Header(navController)
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Column {
                    Text(
                        text = "Sản phẩm",
                    )

                    when {
                        productVM.isLoading.value -> {
                            CircularProgressIndicator()
                        }
                        productVM.products.value?.items.isNullOrEmpty() -> {
                            Text("Không có sản phẩm!")
                        }
                        else -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), // 2 cột
                                modifier = Modifier.padding(8.dp)
                            ) {
                                items(productVM.products.value!!.items) { item ->
                                    Column {
                                        ProductCard(item)
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
    }

}