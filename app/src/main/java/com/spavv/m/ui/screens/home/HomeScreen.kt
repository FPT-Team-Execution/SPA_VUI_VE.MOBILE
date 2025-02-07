package com.spavv.m.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
                    bottom = 100.dp //height of navigation bar
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
            HeaderSection()
            Spacer(modifier = Modifier.height(24.dp))
//            SectionTitle("Dịch vụ")
            Spacer(modifier = Modifier.height(16.dp))
            ServiceGrid()
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Chào, Jennifer!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Chăm sóc bản thân hôm nay nhé!",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Image(
            painter = rememberAsyncImagePainter("https://placehold.co/50x50/png"),
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



