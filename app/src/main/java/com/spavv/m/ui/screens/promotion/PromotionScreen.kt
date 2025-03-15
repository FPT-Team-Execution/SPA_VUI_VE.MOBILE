package com.spavv.m.ui.screens.promotion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.exceptions.UnauthorizedException
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.promotion.PromotionCard
import com.spavv.m.ui.screens.skin_type.SkinTypeVM
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.DarkColor
import kotlinx.coroutines.CoroutineExceptionHandler
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PromotionScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val promotionVM = viewModel<PromotionVM>(
        factory = viewModelFactory {
            PromotionVM(
                MyApp.appModule.promotionDataSource,
            )
        }
    )

    LaunchedEffect(Unit) {
        promotionVM.fetchPromotions {
            navController.navigate(Routes.LOGIN)
        };
    }

    val currentDate = remember { Date() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Ưu đãi đặc biệt",
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
    ) { innerPaddings ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
//                Text(
//                    "Các ưu đãi hiện có",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = DarkColor,
//                    modifier = Modifier.padding(vertical = 16.dp)
//                )
            }

            items(promotionVM.promotions.value) { promotion ->
                PromotionCard(
                    promotion = promotion,
                    currentDate = currentDate
                )
            }
        }
    }
}


// Extension functions for Date formatting
fun Date.formatDateDisplay(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun Number.formatPrice(): String {
    return String.format("%,.0f", this.toDouble())
}












