package com.spavv.m.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.components.home.BannerSlider
import com.spavv.m.ui.components.home.HomeHeader
import com.spavv.m.ui.components.home.ProductItem
import com.spavv.m.ui.components.home.SaleOffer
import com.spavv.m.ui.components.home.SectionTitle
import com.spavv.m.ui.components.home.ServiceItem
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.Pink40

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authVM: AuthVM) {
    val authState = authVM.authState.observeAsState()

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
                .background(Color.White)
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                )
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SpaVuiVe",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Pink40
            )
            Spacer(modifier = Modifier.height(8.dp))
            HomeHeader("Chào, Dat Dev", "Chăm sóc bản thân hôm nay nhé")
            Spacer(modifier = Modifier.height(8.dp))
            BannerSlider(
                images = listOf(
                    "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2023/05/skincare-la-gi-jpg-1685324156-29052023083556.jpg",
                    "https://aladin.com.vn/media/news/1111_mochi-skincare-01.jpg",
                    "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2022/06/skincare-la-gi-jpg-1654847129-10062022144529.jpg"
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            SaleOffer()
            Spacer(modifier = Modifier.height(8.dp))
            SectionTitle("Dịch vụ")
            Spacer(modifier = Modifier.height(8.dp))
            //Services
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                ServiceItem("https://placehold.co/50x50/png","Tư vấn")
                ServiceItem("https://placehold.co/50x50/png","Soi da")
                ServiceItem("https://placehold.co/50x50/png","Đối chiếu")

            }

            SectionTitle("Danh mục")
            Spacer(modifier = Modifier.height(8.dp))
            //Common Category
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                ServiceItem("https://placehold.co/50x50/png","Tinh chất")
                ServiceItem("https://placehold.co/50x50/png","Sữa rửa mặt")
                ServiceItem("https://placehold.co/50x50/png","Mặt nạ")

            }
            SectionTitle("Sản phẩm")
            Spacer(modifier = Modifier.height(8.dp))
            //Common Product
            val products = List(5) { "Product $it" }

            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products) {
                    ProductItem()
                }
            }
        }
    }
}


















