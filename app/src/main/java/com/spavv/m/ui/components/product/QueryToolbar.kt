package com.spavv.m.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spavv.m.ui.screens.product.ProductVM

@Composable
fun QueryToolbar(productVM: ProductVM, navController: NavController, onFilterClick: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ô tìm kiếm
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                productVM.updateQuery(productVM.query.value.copy(filterQuery = it))
            },
            placeholder = { Text("Tìm kiếm sản phẩm...") },
            singleLine = true,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Nút bộ lọc
        IconButton(onClick = onFilterClick) {
            Icon(Icons.Default.FilterList, contentDescription = "Bộ lọc")
        }

        // Nút giỏ hàng
        IconButton(onClick = {
            navController.navigate("cart") {
                popUpTo("home") { inclusive = false }
            }
        }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ hàng")
        }
    }
}
