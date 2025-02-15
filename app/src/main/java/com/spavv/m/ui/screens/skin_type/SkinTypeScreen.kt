package com.spavv.m.ui.screens.skin_type


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.spavv.m.LocalNavigation
import com.spavv.m.data.FakeData
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.skin_test.SkinTypeItem
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SkinTypeScreen(modifier: Modifier) {
    val navController = LocalNavigation.current
    val pagerState = rememberPagerState()

    val skinTypeVM = viewModel<SkinTypeVM>(
        factory = viewModelFactory {
            SkinTypeVM(MyApp.appModule.skinTypeDataSource)
        }
    )
    val skinTypes = skinTypeVM.skinTypes.value ?: emptyList()

    //val skinTypes = listOf(FakeData.fakeSkinType, FakeData.fakeSkinType)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách loại da") },
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
        modifier = modifier
    ) { innerPaddings ->
        Column(
            modifier = Modifier.padding(innerPaddings),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                count = skinTypes.count(),
                modifier = Modifier
            ) { index ->
                SkinTypeItem(skinType = skinTypes[index])
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }

    }

}
