package com.spavv.m.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.spavv.m.LocalNavigation
import com.spavv.m.R
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.home.BannerSlider
import com.spavv.m.ui.components.home.CategoryItem
import com.spavv.m.ui.components.home.HomeHeader
import com.spavv.m.ui.components.home.OfferCard
import com.spavv.m.ui.components.home.ProductCard
import com.spavv.m.ui.components.home.ServiceItem
import com.spavv.m.ui.screens.ScaffoldLayout


@Composable
fun HomeScreen(modifier: Modifier = Modifier, authVM: AuthVM) {
    val navController = LocalNavigation.current
    val authState = authVM.authState.observeAsState()
    val homeVM = viewModel<HomeVM>(
        factory = viewModelFactory {
            HomeVM(MyApp.appModule.productDataSource)
        }
    )

    // Using the client's specified color palette
    val BackgroundItemColor = Color(0xFFe0eeff)
    val BackgroundColor = Color(0xFFf4fbff)
    val PrimaryColor = Color(0xFFfe669d)
    val DarkColor = Color(0xFF33363f)
    val GreyColor = Color(0xFFb4b9bb)

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate(Routes.LOGIN)
            else -> Unit
        }
    }

    ScaffoldLayout(navController) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(
                    top = 24.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 100.dp
                )
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {
            // App Logo
            Text(
                text = "SpaVuiVe",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Header with greeting and avatar
            HomeHeader(
                title = "Chào, Dat Dev",
                subTitle = "Chăm sóc bản thân hôm nay nhé",
                avatar = R.drawable.avatar,
                primaryColor = DarkColor,
                secondaryColor = GreyColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Simplified banner with less distractions
            BannerSlider(
                images = listOf(
                    "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2023/05/skincare-la-gi-jpg-1685324156-29052023083556.jpg",
                    "https://aladin.com.vn/media/news/1111_mochi-skincare-01.jpg",
                    "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2022/06/skincare-la-gi-jpg-1654847129-10062022144529.jpg"
                ),
                indicatorColor = PrimaryColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Minimalist offer card
            OfferCard(
                title = "Ưu đãi đặc biệt",
                subtitle = "Xem khuyến mãi của bạn",
                primaryColor = PrimaryColor,
                backgroundColor = BackgroundItemColor,
                textColor = DarkColor,
                onClick = {
                    navController.navigate(Routes.PROMOTION)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Services section with minimalist design
            Text(
                text = "Dịch vụ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ServiceItem(
                    icon = R.drawable.chat,
                    title = "Tư vấn",
                    primaryColor = PrimaryColor,
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        navController.navigate(Routes.CHAT_BOT)
                    }
                )

                ServiceItem(
                    icon = R.drawable.search,
                    title = "Soi da",
                    primaryColor = PrimaryColor,
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        navController.navigate(Routes.SKIN_TEST)
                    }
                )

                ServiceItem(
                    icon = R.drawable.bookings,
                    title = "Đối chiếu",
                    primaryColor = PrimaryColor,
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        // TODO
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Categories section
            Text(
                text = "Danh mục",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Categories with clean minimal design
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryItem(
                    imageUrl = "https://www.oarsandalps.com/cdn/shop/files/oars-and-alps-spf-50-antioxidant-sunscreen-spray-2_1110x.jpg?v=1732307453",
                    title = "Tinh chất",
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        // TODO
                    }
                )

                CategoryItem(
                    imageUrl = "https://media6.ppl-media.com/mediafiles/blogs/Facial_Toner_0616f44321.jpg",
                    title = "Sữa rửa mặt",
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        // TODO
                    }
                )

               CategoryItem(
                    imageUrl = "https://images.squarespace-cdn.com/content/v1/5c4f6ba1e2ccd1ee6075495d/83bfd75e-3e51-4f26-afa7-30db2a532f68/woman-sheet-face-mask.jpg",
                    title = "Mặt nạ",
                    backgroundColor = BackgroundItemColor,
                    textColor = DarkColor,
                    onClick = {
                        // TODO
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Products section
            Text(
                text = "Sản phẩm",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Product cards with minimalist design
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(homeVM.specialProduct.value) { item ->
                    ProductCard(
                        product = item,
                        primaryColor = PrimaryColor,
                        backgroundColor = Color.White,
                        textColor = DarkColor,
                        secondaryTextColor = GreyColor,
                        borderColor = GreyColor
                    )
                }
            }
        }
    }
}

























