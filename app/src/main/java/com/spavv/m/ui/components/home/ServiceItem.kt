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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor

@Composable
fun ServiceItem(symbol: Any?, title: String, backgroundSize: Double, symbolSize: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.requiredWidth(60.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(backgroundSize.dp)
                .clip(CircleShape)
                .background(BackgroundItemColor)
        ) {
            Image(
                painter = rememberAsyncImagePainter(symbol),
                contentDescription = title,
                modifier = Modifier.size(symbolSize.dp).clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(
            text = title,
            color = DarkColor,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}