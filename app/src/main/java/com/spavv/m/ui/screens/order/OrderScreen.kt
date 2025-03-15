package com.spavv.m.ui.screens.order

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.screens.chatbot.ChatVM

@Composable
fun OrderScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Text(
        text = "Cài đặt", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp), color = Color.Black
    )
}