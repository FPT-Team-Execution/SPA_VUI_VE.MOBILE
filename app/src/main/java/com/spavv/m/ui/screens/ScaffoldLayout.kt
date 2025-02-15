package com.spavv.m.ui.screens


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun ScaffoldLayout ( navController: NavController, child : @Composable (PaddingValues) -> Unit){
    val scaffoldLayoutVM = viewModel<ScaffoldLayoutVM>();

    val selectedIndex = scaffoldLayoutVM.navIndex.value;
    val navItems = listOf(
        BottomNavigationItem(
            title = "Spa",
            selectedIcon = Icons.Rounded.Spa   ,
            unSelectedIcon = Icons.Outlined.Spa,
            hasNew = false,
            path = Routes.HOME
        ),
        BottomNavigationItem(
            title = "Chăm sóc",
            selectedIcon = Icons.Rounded.ShoppingBag ,
            unSelectedIcon = Icons.Outlined.ShoppingBag,
            hasNew = false,
            path = Routes.PRODUCT
        ),
        BottomNavigationItem(
            title = "Yêu thích",
            selectedIcon = Icons.Rounded.Favorite   ,
            unSelectedIcon = Icons.Outlined.FavoriteBorder,
            hasNew = false,
            path = Routes.FAVORITE
        ),
        BottomNavigationItem(
            title = "Hồ sơ",
            selectedIcon = Icons.Rounded.Person   ,
            unSelectedIcon = Icons.Outlined.Person,
            hasNew = false,
            path = Routes.PROFILE
        )
    )
    Scaffold (
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed{index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryColor,
                            unselectedIconColor = GreyColor,
                            selectedTextColor = PrimaryColor,
                            unselectedTextColor = GreyColor,
                            indicatorColor = PrimaryColor.copy(alpha = 0.1f)
                        ),
                        onClick = {
                            scaffoldLayoutVM.updateNavIndex(index);
                            navController.navigate(item.path)

                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null)
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    else if(item.hasNew){
                                        Badge()
                                    }
                                }
                            )
                            {
                                Icon(imageVector =
                                if (selectedIndex == index) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                }
                                    , contentDescription = item.title
                                )
                            }
                        },
                    )
                }
            }
        },
    ){
            innerPadding -> child(innerPadding)
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNew: Boolean,
    val badgeCount: Int? = null,
    val path: String
)