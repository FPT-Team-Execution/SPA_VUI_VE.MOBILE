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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spavv.m.LocalNavigation
import com.spavv.m.R
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.home.BannerSlider
import com.spavv.m.ui.components.home.HomeHeader
import com.spavv.m.ui.components.home.ProductItem
import com.spavv.m.ui.components.home.SaleOffer
import com.spavv.m.ui.components.home.SectionTitle
import com.spavv.m.ui.components.home.ServiceItem
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun HomeScreen(modifier: Modifier = Modifier, authVM: AuthVM) {
    val navController = LocalNavigation.current;
    val authState = authVM.authState.observeAsState()
    val homeVM = viewModel<HomeVM>(
        factory = viewModelFactory {
            HomeVM(MyApp.appModule.productDataSource)
        }
    )
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
                color = PrimaryColor
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                ServiceItem(R.drawable.chat,"Tư vấn",backgroundSize = 60.0, symbolSize = 32.0)
                ServiceItem(R.drawable.search,"Soi da", backgroundSize = 60.0, symbolSize = 32.0)
                ServiceItem(R.drawable.bookings,"Đối chiếu", backgroundSize = 60.0, symbolSize = 32.0)

            }

            SectionTitle("Danh mục")
            Spacer(modifier = Modifier.height(8.dp))
            //Common Category
            Row (
                modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                ServiceItem("https://www.oarsandalps.com/cdn/shop/files/oars-and-alps-spf-50-antioxidant-sunscreen-spray-2_1110x.jpg?v=1732307453","Tinh chất",
                     backgroundSize = 60.0, symbolSize = 52.0)
                ServiceItem("https://media6.ppl-media.com/mediafiles/blogs/Facial_Toner_0616f44321.jpg","Sữa rửa mặt"
                    , backgroundSize = 60.0, symbolSize = 52.0)
                ServiceItem("https://images.squarespace-cdn.com/content/v1/5c4f6ba1e2ccd1ee6075495d/83bfd75e-3e51-4f26-afa7-30db2a532f68/woman-sheet-face-mask.jpg","Mặt nạ"
                    , backgroundSize = 60.0, symbolSize = 52.0)

            }
            SectionTitle("Sản phẩm")
            Spacer(modifier = Modifier.height(8.dp))
            //Common Product
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(homeVM.specialProduct.value) {
                    item -> ProductItem(product = item)
                }
            }
        }
    }
}


















