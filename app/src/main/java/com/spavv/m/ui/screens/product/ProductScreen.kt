package com.spavv.m.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun ProductScreen(modifier: Modifier, navController: NavController) {

    ScaffoldLayout (navController){
            innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Text(text = "Product")

        }
    }

}