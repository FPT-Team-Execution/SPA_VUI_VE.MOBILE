package com.spavv.m.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.theme.SpaVuiVeTheme

@Composable
fun ProfileInformation(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFf4f4f4))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Navigate Icon",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(36.dp)
                )
            }
            Text(
                text = "Profile Information",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp
            )
        }

        Text("Your information", modifier = Modifier.padding(16.dp), fontSize = 18.sp)

        Row() {
            Text("Customer Name");
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileInformationScreen() {
    SpaVuiVeTheme {
        ProfileInformation(modifier = Modifier.fillMaxSize())
    }
}