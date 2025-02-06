package com.spavv.m

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory

import com.spavv.m.ui.screens.home.HomeScreen
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.login.LoginVM
import com.spavv.m.ui.screens.sign_up.SignUpScreen

@Composable
fun MyAppNavigation(modifier: Modifier, authVM: AuthVM)
{
    val navController = rememberNavController()
    //TODO: remove this
    //    val loginVM = viewModel<LoginVM>(
    //                factory = viewModelFactory {
    //                    LoginVM(MyApp.appModule.authDataSource)
    //                }
    //            )
    NavHost(navController = navController, startDestination = Routes.LOGIN, builder = {
        composable(Routes.LOGIN) {
            LoginScreen(modifier = modifier, authVM = authVM, navController = navController)
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(modifier = modifier, authVM = authVM, navController = navController)
        }
        composable(Routes.HOME) {
            HomeScreen(modifier = modifier, authVM = authVM, navController = navController)
        }
    } )
}