package com.spavv.m

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.components.general.Header
import com.spavv.m.ui.screens.cart.CartScreen
import com.spavv.m.ui.screens.favorite.FavoriteScreen
import com.spavv.m.ui.screens.home.HomeScreen
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.product.ProductScreen
import com.spavv.m.ui.screens.profile.ProfileScreen
import com.spavv.m.ui.screens.sign_up.SignUpScreen

@Composable
fun MyAppNavigation(modifier: Modifier, authVM: AuthVM) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN, builder = {
        composable(Routes.LOGIN) {
            LoginScreen(modifier = modifier, authVM = authVM, navController = navController)
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(modifier = modifier, authVM = authVM, navController = navController)
        }
        composable(Routes.HOME) {
            HomeScreen(
                modifier = modifier,
                authVM = authVM,
                navController = navController
            )
        }
        composable(Routes.PRODUCT) {
            ProductScreen(modifier = modifier, navController = navController)
        }
        composable(Routes.FAVORITE) {
            FavoriteScreen(modifier = modifier, navController = navController)
        }
        composable(Routes.PROFILE) {
            ProfileScreen(modifier = modifier, navController = navController)
        }
        composable(Routes.CART) {
            CartScreen(modifier = Modifier, navController = navController)
        }
    })
}