package com.spavv.m.ui.screens.product

import ProductCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.models.Category
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.product.DrawerContent
import com.spavv.m.ui.components.product.QueryToolbar
import com.spavv.m.ui.screens.ScaffoldLayout
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(modifier: Modifier, navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed) // State Drawer
    val scope = rememberCoroutineScope() // Coroutine để mở Drawer

    val productVM = viewModel<ProductVM>(
        factory = viewModelFactory {
            ProductVM(
                MyApp.appModule.productDataSource,
                MyApp.appModule.categoryDataSource
            )
        }
    )

    LaunchedEffect(productVM.getProductsQuery.value) {
        productVM.fetchProducts()
    }

    LaunchedEffect(Unit) {
        productVM.fetchCategories()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                categories = productVM.categories.value?.items,
                onClose = { scope.launch { drawerState.close() } },
                onSelectCategory = { category ->
                    productVM.updateQuery(productVM.getProductsQuery.value.copy(category = category))
                }
            )
        }
    ) {
        ScaffoldLayout(navController) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        bottom = 100.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                QueryToolbar (
                    productVM = productVM,
                    navController = navController,
                    onFilterClick = { scope.launch { drawerState.open() } } // Mở drawer
                )

                productVM.products.value?.let { it1 ->
                    PageSelector(
                        it1.totalPages,
                        productVM.products.value!!.page,
                        onPageChange = { page ->
                            productVM.updateQuery( productVM.getProductsQuery.value.copy(page = page))
                        }
                    )
                }

                Column {
                    when {
                        productVM.isLoading.value -> {
                            CircularProgressIndicator()
                        }

                        productVM.products.value?.items.isNullOrEmpty() -> {
                            Text("Không có sản phẩm!")
                        }

                        else -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.padding(8.dp)
                            ) {
                                items(productVM.products.value!!.items) { item ->
                                    Column {
                                        ProductCard(
                                            item,
                                            onClick = { navController.navigate("${Routes.PRODUCT_DETAIL}/${item.productId}") })
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

@Composable
fun PageSelector(totalPage: Int, page: Int, onPageChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (page > 1) onPageChange(page - 1)
        }) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Giảm")
        }

        androidx.compose.material.Text(
            text = page.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(onClick = {
            if (page < totalPage)
                onPageChange(page + 1)
        }) {
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Tăng")
        }
    }
}