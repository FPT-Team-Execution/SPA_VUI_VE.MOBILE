package com.spavv.m.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.components.general.Header
import com.spavv.m.ui.components.home.PreviewBannerSlider
import com.spavv.m.ui.screens.ScaffoldLayout

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authVM: AuthVM
) {
    val authState = authVM.authState.observeAsState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.HOME) { inclusive = true }
            }
        }
    }

    ScaffoldLayout(navController) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header(navController)
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                PreviewBannerSlider()
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

