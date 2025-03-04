package com.spavv.m.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.spavv.m.R
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor

@Composable
fun HomeHeader(title: String, subTitle: String, avatar: Any? = R.drawable.avatar) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkColor
            )
            Text(
                text = subTitle,
                fontSize = 12.sp,
                color = GreyColor
            )
        }
        Image(
            painter = rememberAsyncImagePainter(avatar),
            contentDescription = "Hồ sơ",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(BorderStroke(1.dp, GreyColor)).clip(CircleShape),
                contentScale = ContentScale.FillBounds
        )
    }
}