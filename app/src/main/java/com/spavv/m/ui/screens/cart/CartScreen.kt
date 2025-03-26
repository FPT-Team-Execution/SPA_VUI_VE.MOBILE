package com.spavv.m.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.models.Item
import com.spavv.m.data.models.payload.AddToCartRequest
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val cartVM = viewModel<CartVM>(
        factory = viewModelFactory {
            CartVM(
                MyApp.appModule.cartDataSource
            )
        }
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        cartVM.fetchCart()
    }

    LaunchedEffect(Unit) {
        cartVM.toastMessages.collectLatest { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Giỏ hàng",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkColor
                        )
                    }
                },
                backgroundColor = BackgroundColor,
                elevation = 0.dp,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .background(BackgroundColor)
            )
        },
        backgroundColor = BackgroundColor,
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(horizontal = 16.dp)
        ) {
            if (cartVM.cart.value.isNullOrEmpty()) {
                EmptyCartView(navController)
            } else {
                CartItemsList(cartVM)
            }
        }
    }
}

@Composable
fun EmptyCartView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Empty Cart",
            tint = Color.Gray,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Giỏ hàng của bạn đang trống",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Vui lòng thêm sản phẩm vào giỏ hàng",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate(Routes.PRODUCT) },
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Tiếp tục mua sắm",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CartItemsList(cartVM: CartVM) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Phần trên có thể cuộn
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "Sản phẩm của bạn",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkColor,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                // Phần danh sách sản phẩm có thể cuộn
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LazyColumn {
                        items(cartVM.cart.value ?: emptyList()) { item ->
                            CartItem(
                                item = item,
                                onQuantityChange = { newQuantity ->
                                    cartVM.updateToCart(
                                        AddToCartRequest(
                                            item.product.productId,
                                            newQuantity
                                        )
                                    )

                                    // TODO: Implement update quantity in CartVM
//                                    cartVM.updateItemQuantity(item.id, newQuantity)
                                },
                                onRemoveItem = {
                                    cartVM.removeFromCart(item.product.productId)
                                    // TODO: Implement remove item in CartVM
//                                    cartVM.removeItem(item.id)
                                }
                            )
                            Divider(
                                color = Color.LightGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            // Phần tổng kết cố định ở dưới
            CartSummary(cartVM)
        }
    }
}

@Composable
fun CartItem(
    item: Item,
    onQuantityChange: (Int) -> Unit,
    onRemoveItem: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Phần hiển thị thông tin sản phẩm
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder for product image
                    AsyncImage(
                        model = item.product.imageUrl,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .fillMaxHeight(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.product.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = DarkColor
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${item.product.price} $",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )
                }

                IconButton(
                    onClick = onRemoveItem
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove item",
                        tint = Color.Red.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Phần điều chỉnh số lượng
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Số lượng:",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Nút giảm số lượng
                QuantityButton(
                    icon = Icons.Default.Remove,
                    contentDescription = "Giảm số lượng",
                    enabled = item.quantity > 1,
                    onClick = {
                        if (item.quantity > 1) {
                            onQuantityChange(item.quantity - 1)
                        }
                    }
                )

                // Hiển thị số lượng hiện tại
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(36.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.quantity.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = DarkColor
                    )
                }

                // Nút tăng số lượng
                QuantityButton(
                    icon = Icons.Default.Add,
                    contentDescription = "Tăng số lượng",
                    enabled = item.quantity < 99,
                    onClick = {
                        if (item.quantity < 99) {
                            onQuantityChange(item.quantity + 1)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun QuantityButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                if (enabled) PrimaryColor.copy(alpha = 0.1f) else Color.LightGray.copy(alpha = 0.3f)
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (enabled) PrimaryColor else Color.Gray,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun CartSummary(cartVM: CartVM) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tổng sản phẩm:",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "${cartVM.cart.value?.size ?: 0} sản phẩm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkColor
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tổng tiền:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkColor
                )
                Text(
                    text = "${calculateTotal(cartVM)} $",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { cartVM.checkout() },
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Đặt hàng",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun calculateTotal(cartVM: CartVM): String {
    val total = cartVM.cart.value?.sumOf {
        it.product.price * it.quantity
    } ?: 0
    return total.toString()
}