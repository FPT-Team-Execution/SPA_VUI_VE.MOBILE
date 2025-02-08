package com.spavv.m.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import com.spavv.m.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.theme.SpaVuiVeTheme


// title = 16sp, subtitle = 14sp, body = 14sp

//
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(CenterHorizontally)
        )

        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 64.dp)
            ) {
                Text(text = "My Review", modifier = Modifier.padding(8.dp))
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 64.dp)
            ) {
                Text(text = "My Order", modifier = Modifier.padding(8.dp))
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Gioi thieu ve App", modifier = Modifier.padding(8.dp))
        }

        Text(
            text = "Quan ly tai khoan", modifier = Modifier.padding(top = 32.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Quan li don hang", modifier = Modifier.padding(8.dp))
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