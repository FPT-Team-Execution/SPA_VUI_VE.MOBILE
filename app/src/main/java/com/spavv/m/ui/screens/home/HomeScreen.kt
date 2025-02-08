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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.spavv.m.comon.constants.Routes
import com.spavv.m.comon.viewModels.AuthState
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.screens.ScaffoldLayout
import com.spavv.m.ui.theme.Pink40
import com.spavv.m.ui.theme.Pink80

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
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Pink40
            )
            Spacer(modifier = Modifier.height(16.dp))
            HeaderSection("Chào, Dat Dev", "Chăm sóc bản thân hôm nay nhé")
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle("Dịch vụ")
            Spacer(modifier = Modifier.height(16.dp))
            //Services
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                ServiceItem("https://placehold.co/50x50/png","Tư vấn")
                ServiceItem("https://placehold.co/50x50/png","Đặt lịch")

            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = rememberAsyncImagePainter("https://placehold.co/300x160/png"),
                contentDescription = "08cd217da88c32763e058a99c2f18a72 1",
                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 149.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp)))
            Spacer(modifier = Modifier.height(16.dp))
            SaleOffer()
            Spacer(modifier = Modifier.height(16.dp))
            ServiceGrid()
        }
    }
}


@Composable
fun SaleOffer(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        border = BorderStroke(2.dp, Color(0xffa7a7a7)),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocalOffer,
                contentDescription = "Sale Icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Nhập mã",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Mã ưu đãi, mã giới thiệu",
                    color = Color.Black.copy(alpha = 0.73f),
                    fontSize = 14.sp
                )
            }
        }
    }
}



@Composable
fun ServiceItem(symbol: Any?, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(50.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xffd0d6da))
        ) {
            Image(
                painter = rememberAsyncImagePainter(symbol),
                contentDescription = title,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = title,
            color = Color(0xff1f2022),
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun HeaderSection(title: String, subTitle: String, avatar: Any? = "https://placehold.co/50x50/png") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = subTitle,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Image(
            painter = rememberAsyncImagePainter(avatar),
            contentDescription = "Hồ sơ",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Pink40,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ServiceGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ServiceItem("Sản phẩm", Icons.Filled.Spa)
            ServiceItem("Tư vấn", Icons.Filled.ChatBubble)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ServiceItem("Ưu đãi", Icons.Filled.LocalOffer)
            ServiceItem("Soi da", Icons.Filled.ContentPasteSearch)
        }
    }
}

@Composable
fun ServiceItem(title: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Pink80)
            .clickable { /* TODO: Handle click */ }
            .padding(12.dp)
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}



