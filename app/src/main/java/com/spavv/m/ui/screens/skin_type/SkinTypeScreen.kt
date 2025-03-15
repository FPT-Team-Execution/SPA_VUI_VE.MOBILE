package com.spavv.m.ui.screens.skin_type


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.FakeData
import com.spavv.m.data.models.SkinType
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.skin_test.SkinTypeItem
import com.spavv.m.ui.theme.BackgroundColor
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SkinTypeScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val pagerState = rememberPagerState()
    val skinTypeVM = viewModel<SkinTypeVM>(
        factory = viewModelFactory {
            SkinTypeVM(MyApp.appModule.skinTypeDataSource)
        }
    )

    LaunchedEffect(Unit) {
        skinTypeVM.fetchSkinTypes(){
            navController.navigate(Routes.LOGIN)
        }
    }



    val skinTypes = skinTypeVM.skinTypes.value ?: emptyList()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Các loại da",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
                },
                backgroundColor = BackgroundColor,
                elevation = 0.dp,
                navigationIcon = {
                    val canGoBack = navController.previousBackStackEntry != null
                    if (canGoBack) {
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
        SkinTypeContent(
            skinTypes = skinTypes,
            pagerState = pagerState,
            modifier = Modifier.padding(innerPaddings)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SkinTypeContent(
    skinTypes: List<SkinType>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Text
        Text(
            text = "Tìm hiểu về loại da của bạn",
            fontSize = 18.sp,
            color = DarkColor.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        // Pager
        HorizontalPager(
            state = pagerState,
            count = skinTypes.count(),
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) { index ->
            SkinTypeCard(skinType = skinTypes[index])
        }

        // Pager Indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp),
            activeColor = PrimaryColor,
            inactiveColor = GreyColor,
            indicatorWidth = 8.dp,
            indicatorHeight = 8.dp,
            spacing = 12.dp
        )
    }
}

@Composable
private fun SkinTypeCard(skinType: SkinType) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        backgroundColor = BackgroundItemColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Skin Type Title
            Text(
                text = skinType.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Description
            DetailSection(
                title = "Miêu tả",
                icon = Icons.Default.Description,
                content = skinType.description ?: "Chưa cập nhật",
                iconTint = PrimaryColor
            )

            // Characteristics
            DetailSection(
                title = "Đặc điểm",
                icon = Icons.Default.Menu,
                content = skinType.characteristics ?: "Chưa cập nhật",
                iconTint = PrimaryColor
            )

            // Recommended
            DetailSection(
                title = "Thành phần phù hợp",
                icon = Icons.Default.Check,
                content = skinType.recommendedIngredients ?: "Chưa cập nhật",
                iconTint = Color.Green
            )

            // Avoid
            DetailSection(
                title = "Thành phần cần tránh",
                icon = Icons.Default.Close,
                content = skinType.avoidIngredients ?: "Chưa cập nhật",
                iconTint = Color.Red
            )

            // Care Instructions
            DetailSection(
                title = "Hướng dẫn chăm sóc",
                icon = Icons.Default.Spa,
                content = skinType.careInstructions ?: "Chưa cập nhật",
                iconTint = PrimaryColor
            )
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    icon: ImageVector,
    content: String,
    iconTint: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = DarkColor
            )
        }

        Text(
            text = content,
            fontSize = 16.sp,
            color = DarkColor.copy(alpha = 0.8f),
            lineHeight = 24.sp,
            modifier = Modifier.padding(start = 36.dp)
        )
    }
}
