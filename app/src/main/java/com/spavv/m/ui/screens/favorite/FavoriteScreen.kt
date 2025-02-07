package com.spavv.m.ui.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.spavv.m.ui.screens.ScaffoldLayout

//import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun FavoriteScreen(modifier: Modifier, navController: NavController) {
    ScaffoldLayout (navController){
            innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Text(text = "Fa")
            Text(text = "vo")
            Text(text = "rite")
        }
    }

}