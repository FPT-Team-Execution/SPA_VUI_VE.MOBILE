package com.spavv.m.ui.screens.skin_test


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spavv.m.ui.components.general.ModernTopAppBar
import com.spavv.m.ui.components.skin_test.ProgressSection
import com.spavv.m.ui.components.skin_test.QuestionsSection
import com.spavv.m.ui.components.skin_test.SubmitButton
import com.spavv.m.ui.theme.BackgroundColor


@Composable
fun SkinTestScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val skinTestVm = viewModel<SkinTestVM>(
        factory = viewModelFactory {
            SkinTestVM(MyApp.appModule.skinTestDataSource)
        }
    )

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val skinTestQuestions = skinTestVm.skinTestQuestions.value

    LaunchedEffect(Unit) {
        skinTestVm.fetchQuestions(){
            navController.navigate(Routes.LOGIN)
        }
    }

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

    Scaffold(
        topBar = {
            ModernTopAppBar(
                title = "Soi da",
                navController = navController,
                onInfoClick = { navController.navigate(Routes.SKIN_TYPE) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.fillMaxSize(),
        backgroundColor = BackgroundColor // Sử dụng màu nền chính
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .padding(innerPaddings)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Progress Section
            ProgressSection(
                current = skinTestVm.skinTestOptions.value.size,
                total = skinTestQuestions.size
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Questions Section
            QuestionsSection(
                questions = skinTestQuestions,
                skinTestVm = skinTestVm,
                modifier = Modifier.weight(1f)
            )

            // Submit Button
            SubmitButton(
                onSubmit = {
                    coroutineScope.launch {
                        val result = skinTestVm.submitSkinTest{
                            navController.navigate(Routes.LOGIN)
                        };
                        if (result && skinTestVm.skinTypeResult.value != null) {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "skinTestResult",
                                skinTestVm.skinTypeResult.value
                            )
                            navController.navigate(Routes.SKIN_TEST_RESULT)
                        }
                    }
                }
            )
        }
    }
}


















