package com.spavv.m.ui.screens.product

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun DetailScreen(modifier: Modifier = Modifier, productId: String, navController: NavController) {

    val productVM = viewModel<ProductVM>(
        factory = viewModelFactory {
            ProductVM(
                MyApp.appModule.productDataSource,
                MyApp.appModule.categoryDataSource
            )
        }
    )

    var quantity by remember { mutableStateOf(1) }

    LaunchedEffect(productId) {
        productVM.fetchProduct(productId)
    }

    ScaffoldLayout(
        navController
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    bottom = 100.dp
                ),
        ) {
            TopAppBar(
                title = { androidx.compose.material3.Text("Sản phẩm") },
                backgroundColor = PrimaryColor,
                navigationIcon = {
                    val canGoBack = navController.previousBackStackEntry != null
                    if (canGoBack) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        //*Redirect to SkinTypeScreen
                        navController.navigate(Routes.CART)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Giỏ hàng",
                            tint = DarkColor
                        )
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ảnh sản phẩm
                AsyncImage(
                    model = productVM.product.value?.imageUrl,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = modifier
                        .padding(16.dp),
                ) {
                    productVM.product.value?.let { it1 ->
                        Text(
                            text = it1.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Thông tin thương hiệu và danh mục
                    Text(text = "Thương hiệu: ${productVM.product.value?.brand?.name ?: "Không rõ"}")
                    Text(text = "Danh mục: ${productVM.product.value?.category?.name ?: "Không rõ"}")

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mô tả sản phẩm
                    productVM.product.value?.let { it1 ->
                        Text(
                            text = it1.description,
                            textAlign = TextAlign.Justify
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Giá sản phẩm
                    Text(
                        text = "$ ${productVM.product.value?.price}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red // Màu xanh lá
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(

                    ) {
                        // Điều chỉnh số lượng
                        productVM.product.value?.stockQuantity?.let { it1 ->
                            QuantitySelector(
                                stockQuantity = it1,
                                quantity,
                                onQuantityChange = { newQuantity ->
                                    quantity = newQuantity
                                }
                            )
                        }

                        // Nút thêm vào giỏ hàng
                        Button(
                            onClick = { Unit },
                            colors = ButtonDefaults.buttonColors(Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Thêm")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun QuantitySelector(stockQuantity: Int, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (quantity > 1) onQuantityChange(quantity - 1)
        }) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Giảm")
        }

        Text(
            text = quantity.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(onClick = {
            if (quantity < stockQuantity)
                onQuantityChange(quantity + 1)
        }) {
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Tăng")
        }
    }
}