package com.spavv.m.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun DetailScreen(modifier: Modifier = Modifier, productId: String, navController: NavController) {
    ScaffoldLayout(navController){
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    bottom = 100.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = productId)
        }
    }
}