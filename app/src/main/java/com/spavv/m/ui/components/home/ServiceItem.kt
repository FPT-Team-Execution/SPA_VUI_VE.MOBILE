package com.spavv.m.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ServiceItem(symbol: Any?, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.requiredWidth(60.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xffd0d6da))
        ) {
            Image(
                painter = rememberAsyncImagePainter(symbol),
                contentDescription = title,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = title,
            color = Color(0xff1f2022),
            fontSize = 12.sp,
            maxLines = 1,  // Giới hạn 1 dòng
            overflow = TextOverflow.Ellipsis,  // Hiển thị "..." nếu quá dài
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}