package com.spavv.m

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spavv.m.comon.Routes

import com.spavv.m.ui.screens.home.HomeScreen
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.login.LoginVM
import com.spavv.m.ui.screens.sign_up.SignUpScreen
import okhttp3.Route

@Composable
fun MyAppNavigation(modifier: Modifier, loginVM: LoginVM)
{
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.LOGIN, builder = {
        composable(Routes.LOGIN) {
            LoginScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
        composable(Routes.HOME) {
            HomeScreen(modifier = modifier, loginVM = loginVM, navController = navController)
        }
    } )
}