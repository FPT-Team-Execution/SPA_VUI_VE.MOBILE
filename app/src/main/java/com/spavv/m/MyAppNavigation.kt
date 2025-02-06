package com.spavv.m

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.spavv.m.ui.screens.home.HomeScreen
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.login.LoginVM
import com.spavv.m.ui.screens.sign_up.SignUpScreen

@Composable
fun MyAppNavigation(modifier: Modifier, loginVM: LoginVM)
{
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
        composable("signup") {
            SignUpScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
        composable("home") {
            HomeScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
    } )
}