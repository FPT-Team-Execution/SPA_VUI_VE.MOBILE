package com.spavv.m.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.models.payload.AddToCartRequest
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun DetailScreen(modifier: Modifier = Modifier, productId: String, navController: NavController) {
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

    var quantity by remember { mutableStateOf(1) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(productId) {
        productVM.fetchProduct(productId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chi tiết sản phẩm",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Routes.CART)
                    }) {
                        Box {
                            Icon(
                                imageVector = Icons.Rounded.ShoppingCart,
                                contentDescription = "Giỏ hàng",
                                tint = PrimaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                            // Optional: Badge for cart items count
//                            if ((productVM.cartItemCount.value ?: 0) > 0) {
//                                Box(
//                                    modifier = Modifier
//                                        .size(16.dp)
//                                        .background(Color.Red, CircleShape)
//                                        .align(Alignment.TopEnd),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text(
//                                        text = (productVM.cartItemCount.value ?: 0).toString(),
//                                        color = Color.White,
//                                        fontSize = 10.sp,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                }
//                            }
                        }
                    }
                },
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.White, Color.White.copy(alpha = 0.95f))
                        )
                    )
            )
        },
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            Surface(
                elevation = 8.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Price section
                    Column {
                        Text(
                            text = "Giá",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "$ ${productVM.product.value?.price}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )
                    }

                    // Add to cart button
                    Button(
                        onClick = {
                            productVM.addToCart(
                                AddToCartRequest(
                                    productId,
                                    quantity
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(PrimaryColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .width(180.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Add to Cart",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Thêm vào giỏ",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(
                    top = innerPaddings.calculateTopPadding(),
                    bottom = innerPaddings.calculateBottomPadding(),
                    start = 0.dp,
                    end = 0.dp
                ))
                .verticalScroll(scrollState)
        ) {
            // Product image with overlay gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xFFF5F5F5))
            ) {
                AsyncImage(
                    model = productVM.product.value?.imageUrl,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Product details card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Product name
                    productVM.product.value?.let { product ->
                        Text(
                            text = product.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkColor
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Brand and category with icons
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Brand",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "  ${productVM.product.value?.brand?.name ?: "Unknown brand"}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = "Category",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "  ${productVM.product.value?.category?.name ?: "Unknown category"}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quantity selector with improved UI
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Số lượng:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        productVM.product.value?.stockQuantity?.let { stockQuantity ->
                            QuantitySelector(
                                stockQuantity = stockQuantity,
                                quantity = quantity,
                                onQuantityChange = { newQuantity ->
                                    quantity = newQuantity
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Product description section
                    Text(
                        text = "Mô tả sản phẩm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    productVM.product.value?.let { product ->
                        Text(
                            text = product.description,
                            textAlign = TextAlign.Justify,
                            lineHeight = 24.sp,
                            color = Color.DarkGray
                        )
                    }

                    // Extra spacing at bottom to ensure content isn't hidden by bottom bar
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(stockQuantity: Int, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .height(40.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Color(0xFFF5F5F5)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            IconButton(
                onClick = {
                    if (quantity > 1) onQuantityChange(quantity - 1)
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = "Decrease",
                    tint = if (quantity > 1) PrimaryColor else Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = quantity.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = DarkColor
            )

            IconButton(
                onClick = {
                    if (quantity < stockQuantity) onQuantityChange(quantity + 1)
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase",
                    tint = if (quantity < stockQuantity) PrimaryColor else Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}