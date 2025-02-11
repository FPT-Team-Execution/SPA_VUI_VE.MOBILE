package com.spavv.m.ui.screens.skin_test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun ResultScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current

    val skinTestVm = viewModel<SkinTestVM>(
        factory = viewModelFactory {
            SkinTestVM(MyApp.appModule.skinTestDataSource)
        }
    )
//    LaunchedEffect(skinTestVm.skinTypeResult.value) {
//        if(skinTestVm.skinTypeResult.value != null){
//            navController.navigate(Routes.SKIN_TEST)
//        }
//    }

    val skinTypeResult = FakeData.fakeSkinType

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kết quả") },
                backgroundColor = PrimaryColor,
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
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.result_img),
                contentDescription = "Background",
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            SkinTypeItem(skinType = skinTypeResult)
        }
    }
}

@Composable
fun SkinTypeItem( skinType: SkinType) {
    Column(
        modifier = Modifier.padding(8.dp)
            .border(1.dp, color = GreyColor)
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = skinType.name,
            color = DarkColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(8.dp)

        ) {
            DetailSection(text = "Miêu tả", icon = Icons.Default.Description) {
                skinType.description
            }
            DetailSection(text = "Thành phần", icon = Icons.Default.Menu){
                skinType.characteristics
            }
            DetailSection(text = "Đề xuất", icon = Icons.Default.Check, Color.Green){
                skinType.recommendedIngredients
            }
            DetailSection(text = "Nên tránh", icon = Icons.Default.Close, Color.Red){
                skinType.avoidIngredients
            }
            DetailSection(text = "Chăm sóc da", icon = Icons.Default.Spa, PrimaryColor){
                skinType.careInstructions
            }
        }
    }
}

@Composable
fun DetailSection(text: String, icon: ImageVector? = null, color: Color = DarkColor, content: () -> String = {"Chưa cập nhật"} ) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = text, tint = color)
        }
        Text(text = text, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)

    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = content.invoke(), color = Color.Black, fontSize = 12.sp)
}





