package com.spavv.m.ui.screens.profile

import AboutAppButton
import ButtonBottom
import LogoutButton
import ButtonMiddle
import ButtonTop
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import com.spavv.m.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.components.profile.*
import com.spavv.m.ui.theme.SpaVuiVeTheme

// title = 16sp, subtitle = 12sp, body = 14sp
// gap = 8dp, padding = 16dp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFf4f4f4))
            .verticalScroll(rememberScrollState())
    ) {
        Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0x10bcf9a5), Color(0x107fdcf9))
                ),
            ),
        )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = R.drawable.img_login),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(CenterHorizontally)
            )

            Text(
                text = "User Name",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 64.dp)
                    .align(CenterHorizontally)
            )
        }

    }
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Đánh giá của tôi", modifier = Modifier.padding(4.dp))
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Ưu đãi của tôi", modifier = Modifier.padding(4.dp))
                }
            }

            AboutAppButton()

            Text(
                text = "Quản lý tài khoản", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp)
            )

            ButtonTop(
                customIcon = Icons.Default.ListAlt,
                text = "Quản lý đơn hàng",
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 8.dp)
            )

            ButtonBottom(
                customIcon = Icons.Default.AccountCircle,
                text = "Quản lý tài khoản",
                onClick = { /*TODO*/ },
            )

            Text(
                text = "Lan tỏa cùng Spa Vui Vẻ", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp)
            )

            ButtonTop(
                customIcon = Icons.Default.ThumbUp,
                text = "Đánh giá ứng dụng",
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 8.dp)
            )

            ButtonMiddle(
                customIcon = Icons.Default.PersonAddAlt,
                text = "Giới thiệu bạn bè",
                onClick = { /*TODO*/ },
            )

            ButtonBottom(
                customIcon = Icons.Default.Share,
                text = "Chia sẻ ứng dụng",
                onClick = { /*TODO*/ },
            )

            Text(
                text = "Cài đặt", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp)
            )

            ButtonTop(
                customIcon = Icons.Default.Language,
                text = "Ngôn ngữ",
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(top = 8.dp)
            )

            ButtonBottom(
                customIcon = Icons.Default.Article,
                text = "Điều khoản sử dụng",
                onClick = { /*TODO*/ },
            )

            LogoutButton()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    SpaVuiVeTheme {
        ProfileScreen(modifier = Modifier.fillMaxSize())
    }
}