package com.spavv.m.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.screens.login.LoginVM

@Composable
fun HomeScreen(modifier: Modifier,navController: NavController, authVM: AuthVM) {
    val authState = authVM.authState.observeAsState()
    val context = LocalContext.current;
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(Routes.LOGIN)
            else -> Unit //nothing
        }
    }
    Column {
        Text(text = "Home")
    }
}