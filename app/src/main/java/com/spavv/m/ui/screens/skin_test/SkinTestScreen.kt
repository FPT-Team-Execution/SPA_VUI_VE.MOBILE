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
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.data.FakeData
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.general.MyProcessIndicator
import com.spavv.m.ui.components.skin_test.Answer
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor
@Composable
fun SkinTestScreen(modifier: Modifier) {


    val navController = LocalNavigation.current

    val skinTestVm = viewModel<SkinTestVM>(
        factory = viewModelFactory {
            SkinTestVM(MyApp.appModule.skinTestDataSource)
        }
    )
    LaunchedEffect(skinTestVm.skinTestOptions.value) {

    }
    val skinTestQuestions = skinTestVm.skinTestQuestions.value
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
                    items(skinTestQuestions) { questionItem ->
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
                                        //* Add option
                                        skinTestVm.updateOrAddOption(questionItem.questionId, option)
                                    },
                                        isChosen = skinTestVm.skinTestOptions.value.values.any {
                                            it.optionId.equals(option.optionId)}

                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




