package com.spavv.m.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.screens.ScaffoldLayoutVM

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController, authVM: AuthVM) {
    val authState = authVM.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate(Routes.LOGIN)
            else -> Unit //nothing
        }
    }
    ScaffoldLayout (navController){
         innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            Text(text = "Home")
            Text(text = "Home")
            Text(text = "Home")
        }
    }
//    Column(
//        modifier = modifier.fillMaxSize()
//    ) {
//        Text(text = "Home")
//        Text(text = "Home")
//        Text(text = "Home")
//    }
}
