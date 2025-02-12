package com.spavv.m.ui.components.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.R

@Composable
fun ChatMessage(
    text: String,
    isBot: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isBot) Arrangement.Start else Arrangement.End
    ) {
        if (isBot) {
            AvatarsSender(modifier = Modifier.padding(end = 8.dp), R.drawable.chatbot)
        }

        Box(
            modifier = Modifier
                .wrapContentWidth() // Cho phép kích thước thay đổi theo nội dung
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomEnd = if (isBot) 12.dp else 0.dp,
                        bottomStart = if (isBot) 0.dp else 12.dp
                    )
                )
                .background(if (isBot) Color(0xFFB3E5FC) else Color(0xffdee2e6))
                .padding(12.dp)
        ) {
            Text(
                text = text,
                color = Color.DarkGray,
                fontSize = 15.sp
            )
        }

        if (!isBot) {
            AvatarsSender(modifier = Modifier.padding(start = 8.dp), R.drawable.avatar)
        }
    }
}