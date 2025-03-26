package com.spavv.m.ui.screens.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.screens.chatbot.ChatVM
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.DarkColor

@Composable
fun OrderScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Quản lý đơn hàng",
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
    ) {
            innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {

        }
    }
}