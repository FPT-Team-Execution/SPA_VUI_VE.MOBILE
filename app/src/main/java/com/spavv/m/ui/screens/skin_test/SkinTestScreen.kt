package com.spavv.m.ui.screens.skin_test


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.general.MyProcessIndicator
import com.spavv.m.ui.components.skin_test.Answer
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
@Composable
fun SkinTestScreen(modifier: Modifier) {


    val navController = LocalNavigation.current

    val skinTestVm = viewModel<SkinTestVM>(
        factory = viewModelFactory {
            SkinTestVM(MyApp.appModule.skinTestDataSource)
        }
    )


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
//    val context = LocalContext.current
    LaunchedEffect(Unit) {
        skinTestVm.toastMessages.collectLatest { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
            }
        }
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
                actions = {
                    IconButton(onClick = {
                        //*Redirect to SkinTypeScreen
                        navController.navigate(Routes.SKIN_TYPE)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.LocalLibrary,
                            contentDescription = "Các loại da",
                            tint = DarkColor
                        )
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
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
                MyProcessIndicator(currentPercent = skinTestVm.skinTestOptions.value.size.toFloat() / skinTestVm.skinTestQuestions.value.size.toFloat())
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
                                            it.optionId == option.optionId
                                        }

                                    )
                                }
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {
                    //* Submit Skin Test
                    coroutineScope.launch {
                        val result = skinTestVm.submitSkinTest()
                        if (result && navController.currentBackStackEntry?.savedStateHandle != null && skinTestVm.skinTypeResult.value != null) {
                            // Lưu vào savedStateHandle trước khi navigate
                            navController.currentBackStackEntry?.savedStateHandle?.set("skinTestResult", skinTestVm.skinTypeResult.value)

                            // Chuyển sang màn hình Result
                            navController.navigate(Routes.SKIN_TEST_RESULT)
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = PrimaryColor, // Màu nền của button
                    contentColor = DarkColor // Màu chữ
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Hoàn thành")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Hoàn thành", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}




