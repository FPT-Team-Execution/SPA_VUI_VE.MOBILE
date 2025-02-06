package com.spavv.m

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.screens.login.LoginScreen
import com.spavv.m.ui.screens.login.LoginVM
import com.spavv.m.ui.theme.SpaVuiVeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginVM = viewModel<LoginVM>(
                factory = viewModelFactory {
                    LoginVM(MyApp.appModule.authDataSource)
                }
            )
            SpaVuiVeTheme {
                MyAppNavigation(modifier = Modifier.fillMaxSize(), loginVM = loginVM)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSpace() {
//    SpaVuiVeTheme {
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//            content = { innerPadding ->
//                MyAppNavigation(modifier = Modifier.padding(innerPadding), loginVM = loginVM)
//            },)
//
//    }
//}
