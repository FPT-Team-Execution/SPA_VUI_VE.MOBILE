package com.spavv.m.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spavv.m.data.models.Product

@Composable
fun ProductCard(
    product: Product
) {
    Text(product.name)
}