package com.spavv.m.ui.screens.login

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.R
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory

@Composable
fun LoginScreen(paddingValues: PaddingValues) {
    val loginVM = viewModel<LoginVM>(
        factory = viewModelFactory {
            LoginVM(MyApp.appModule.authDataSource)
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Spa Vui Vẻ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
            )
        Image(
            painter = painterResource(id = R.drawable.img_login),
            contentDescription = "Login image",
            modifier = Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Chào mừng quay lại",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Đăng nhập với tài khoản",
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = loginVM.email.value,
            onValueChange = {
              loginVM.updateEmail(it)
            },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = loginVM.email.value,
            onValueChange = {
                value : String -> loginVM.updatePassword(value)
            },
            label = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "Đăng nhập",
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Quên mật khẩu ?",
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hoặc đăng nhập với",
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "facebook",
                modifier = Modifier.size(40.dp).clickable {
                    /*TODO*/
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google",
                modifier = Modifier.size(40.dp).clickable {
                    /*TODO*/
                }
            )
        }

    }
}
