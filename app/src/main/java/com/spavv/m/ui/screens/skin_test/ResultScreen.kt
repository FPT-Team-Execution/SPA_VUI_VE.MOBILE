package com.spavv.m.ui.screens.skin_test


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.R
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.FakeData
import com.spavv.m.data.models.SkinType
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.general.ModernTopAppBar
import com.spavv.m.ui.components.skin_test.SkinTypeItem
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun ResultScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val result = navController.previousBackStackEntry?.savedStateHandle?.get<SkinType>("skinTestResult")

    LaunchedEffect(Unit) {
        if(result == null){
            navController.navigate(Routes.SKIN_TEST)
        }
    }

    Scaffold(
        topBar = {
            ModernTopAppBar(
                title = "Soi da",
                navController = navController,
                onInfoClick = { navController.navigate(Routes.SKIN_TYPE) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        if(result != null) {
            ResultContent(
                skinType = result,
                modifier = Modifier.padding(innerPaddings)
            )
        }
    }
}

@Composable
private fun ResultContent(
    skinType: SkinType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header Section
        ResultHeader(skinType = skinType)

        Spacer(modifier = Modifier.height(24.dp))

        // Details Section
        ResultDetails(skinType = skinType)
    }
}

@Composable
private fun ResultHeader(skinType: SkinType) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.result_img),
            contentDescription = "Result Image",
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = skinType.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ResultDetails(skinType: SkinType) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = BackgroundItemColor,
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Description Section
            DetailItem(
                title = "Miêu tả",
                icon = Icons.Default.Description,
                content = skinType.description,
                iconTint = PrimaryColor
            )

            // Characteristics Section
            DetailItem(
                title = "Đặc điểm",
                icon = Icons.Default.Menu,
                content = skinType.characteristics,
                iconTint = PrimaryColor
            )

            // Recommended Section
            DetailItem(
                title = "Thành phần phù hợp",
                icon = Icons.Default.Check,
                content = skinType.recommendedIngredients,
                iconTint = Color.Green
            )

            // Avoid Section
            DetailItem(
                title = "Thành phần cần tránh",
                icon = Icons.Default.Close,
                content = skinType.avoidIngredients,
                iconTint = Color.Red
            )

            // Care Instructions
            DetailItem(
                title = "Hướng dẫn chăm sóc",
                icon = Icons.Default.Spa,
                content = skinType.careInstructions,
                iconTint = PrimaryColor
            )
        }
    }
}

@Composable
private fun DetailItem(
    title: String,
    icon: ImageVector,
    content: String?,
    iconTint: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = content ?: "Chưa cập nhật",
            fontSize = 16.sp,
            color = DarkColor.copy(alpha = 0.8f),
            lineHeight = 24.sp
        )
    }
}







