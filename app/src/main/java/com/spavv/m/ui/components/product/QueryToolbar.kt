package com.spavv.m.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.ui.screens.product.ProductVM
import com.spavv.m.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryToolbar(
    productVM: ProductVM,
    navController: NavController,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val cartItemCount = 0

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp)),
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search field with icon
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    productVM.updateQuery(productVM.getProductsQuery.value.copy(filterQuery = it))
                },
                placeholder = { Text("Tìm kiếm sản phẩm...", fontSize = 14.sp) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    cursorColor = PrimaryColor,
//                    containerColor = Color(0xFFF5F5F5)
//                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Filter button
            IconButton(
                onClick = onFilterClick,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                Icon(
                    Icons.Default.FilterList,
                    contentDescription = "Filters",
                    tint = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Cart button with badge
            Box(contentAlignment = Alignment.TopEnd) {
                IconButton(
                    onClick = {
                        navController.navigate(Routes.CART)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryColor.copy(alpha = 0.1f))
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = PrimaryColor
                    )
                }

//                // Badge for cart items
//                if (cartItemCount.value > 0) {
//                    Box(
//                        modifier = Modifier
//                            .size(18.dp)
//                            .offset(x = 4.dp, y = (-4).dp)
//                            .clip(CircleShape)
//                            .background(Color.Red),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = if (cartItemCount.value > 9) "9+" else cartItemCount.value.toString(),
//                            color = Color.White,
//                            fontSize = 10.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
            }
        }
    }
}