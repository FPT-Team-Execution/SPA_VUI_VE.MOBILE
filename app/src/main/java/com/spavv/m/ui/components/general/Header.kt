package com.spavv.m.ui.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Tìm kiếm...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.95f),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                }
            )
        },
        actions = {
            IconButton(onClick = {navController.navigate("cart") {
                popUpTo("cart") { inclusive = true }
            }}) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Giỏ hàng")
            }
        }
    )
}
