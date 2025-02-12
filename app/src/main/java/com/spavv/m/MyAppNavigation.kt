package com.spavv.m

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.screens.cart.CartScreen
import com.spavv.m.ui.screens.chatbot.ChatScreen
import com.spavv.m.ui.screens.favorite.FavoriteScreen
import com.spavv.m.ui.screens.home.HomeScreen
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.product.ProductScreen
import com.spavv.m.ui.screens.profile.ProfileScreen
import com.spavv.m.ui.screens.sign_up.SignUpScreen
import com.spavv.m.ui.screens.skin_test.ResultScreen
import com.spavv.m.ui.screens.skin_test.SkinTestScreen
import com.spavv.m.ui.screens.skin_type.SkinTypeScreen

@Composable
fun MyAppNavigation(modifier: Modifier) {
    val navController = LocalNavigation.current
    val authVM = viewModel<AuthVM>();
    NavHost(navController = navController, startDestination = Routes.HOME, builder = {
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
            )
        }
        composable(Routes.SKIN_TYPE) {
            SkinTypeScreen( modifier = modifier)
        }
        //Nest route from HOME
        composable(Routes.SKIN_TEST) {
            SkinTestScreen(modifier = modifier)
        }
        composable(Routes.SKIN_TEST_RESULT) {
            ResultScreen(modifier = modifier)
        }
        composable(Routes.CHAT_BOT) {
            ChatScreen(modifier = modifier)
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