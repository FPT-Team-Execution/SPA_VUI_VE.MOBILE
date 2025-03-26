package com.spavv.m.ui.screens.product

import ProductCard
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.product.DrawerContent
import com.spavv.m.ui.components.product.QueryToolbar
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.PrimaryColor
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(modifier: Modifier, navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed) // State Drawer
    val scope = rememberCoroutineScope() // Coroutine để mở Drawer

    val productVM = viewModel<ProductVM>(
        factory = viewModelFactory {
            ProductVM(
                    MyApp.appModule.productDataSource,
                MyApp.appModule.categoryDataSource,
                MyApp.appModule.brandDataSource,
                MyApp.appModule.cartDataSource
            )
        }
    )

    LaunchedEffect(productVM.getProductsQuery.value) {
        productVM.fetchProducts()
    }

    LaunchedEffect(Unit) {
        productVM.fetchCategories()
        productVM.fetchBrands()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                categories = productVM.categories.value?.items,
                brands = productVM.brands.value?.items,
                onClose = { scope.launch { drawerState.close() } },
                onSelectCategory = { category ->
                    productVM.updateQuery(productVM.getProductsQuery.value.copy(category = category))
                },
                onSelectBrand = { brand ->
                    productVM.updateQuery(productVM.getProductsQuery.value.copy(brand = brand))
                },
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

                QueryToolbar(
                    productVM = productVM,
                    navController = navController,
                    onFilterClick = { scope.launch { drawerState.open() } } // Mở drawer
                )

                productVM.products.value?.let { it1 ->
                    PageSelector(
                        it1.totalPages,
                        productVM.products.value!!.page,
                        onPageChange = { page ->
                            productVM.updateQuery(productVM.getProductsQuery.value.copy(page = page))
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
fun PageSelector(
    totalPage: Int,
    page: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5))
            .padding(4.dp)
    ) {
        // Previous page button
        IconButton(
            onClick = { if (page > 1) onPageChange(page - 1) },
            enabled = page > 1,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Page",
                tint = if (page > 1) PrimaryColor else Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Page display
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(PrimaryColor.copy(alpha = 0.1f))
                .border(1.dp, PrimaryColor.copy(alpha = 0.3f), CircleShape)
        ) {
            Text(
                text = "$page",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                textAlign = TextAlign.Center
            )
        }

        // Page count indicator
        Text(
            text = "/ $totalPage",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        // Next page button
        IconButton(
            onClick = { if (page < totalPage) onPageChange(page + 1) },
            enabled = page < totalPage,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Page",
                tint = if (page < totalPage) PrimaryColor else Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}