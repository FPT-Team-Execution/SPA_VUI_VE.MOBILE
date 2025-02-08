package com.spavv.m.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spavv.m.ui.components.general.Header
import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun ProductScreen(modifier: Modifier, navController: NavController) {


    ScaffoldLayout(navController) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header(navController)
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Column {
                    Text(text = "Danh mục")
                    Row {

                    }
                }
                Column {
                    Text(text = "Tiêu biểu")
                }
            }
        }
    }

}