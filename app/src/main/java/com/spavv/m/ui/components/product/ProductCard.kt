package com.spavv.m.ui.components.product

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spavv.m.data.models.Product

@Composable
fun ProductCard(
//    modifier: Modifier = Modifier,
    product: Product
) {
    Text(product.name)
}