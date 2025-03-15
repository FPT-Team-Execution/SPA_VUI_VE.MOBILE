package com.spavv.m.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.LocalNavigation
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.SpaVuiVeTheme

@Composable
fun ProfileDetailScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = "Quản lý tài khoản",
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Navigate Icon",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(36.dp)
                )
            }
            Text(
                text = "Profile Information",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp
            )
        }

        Text("Your information", modifier = Modifier.padding(16.dp), fontSize = 18.sp)

        Row() {
            Text("Customer Name");
        }
    }}
}