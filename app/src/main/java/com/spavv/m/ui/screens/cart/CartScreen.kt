package com.spavv.m.ui.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.chatbot.ChatInput
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

//import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun CartScreen(modifier: Modifier) {

    val navController = LocalNavigation.current;
    val cartVM = viewModel<CartVM>(
        factory = viewModelFactory {
            CartVM(
                MyApp.appModule.cartDataSource
            )
        }
    )

    LaunchedEffect(Unit) {
        cartVM.fetchCart()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tư vấn",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
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
                modifier = Modifier.shadow(elevation = 4.dp)
            )
        },
        backgroundColor = BackgroundColor,
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {

        }
    }
}