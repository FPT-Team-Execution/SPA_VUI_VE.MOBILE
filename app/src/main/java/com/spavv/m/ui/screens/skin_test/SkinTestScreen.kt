package com.spavv.m.ui.screens.skin_test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.LocalNavigation
import com.spavv.m.data.FakeData
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.ui.components.general.MyProcessIndicator
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor
@Composable
fun SkinTestScreen(modifier: Modifier) {
    val navController = LocalNavigation.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Soi da") },
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
            modifier = modifier
                .padding(innerPaddings)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Text("Tiến trình", fontSize = 16.sp)
                MyProcessIndicator(currentPercent = 0.2f)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(FakeData.mockSkinTestQuestions) { questionItem ->
                        Column (
                            modifier = Modifier.padding(bottom = 8.dp)
                        ){
                            Text(
                                questionItem.question,
                                color = DarkColor,
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                questionItem.skinTestOptions.forEach { option ->
                                    Answer(option, onClick = {
                                        // TODO: Xử lý sự kiện click
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Answer(option: SkinTestOption, onClick: () -> Unit ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = BackgroundItemColor)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .clickable {
                onClick()
            }
    ){
        Text(
            text = option.optionText,
            color = DarkColor,
            textAlign = TextAlign.Center,
            softWrap = true,
            fontSize = 12.sp)
    }
}


