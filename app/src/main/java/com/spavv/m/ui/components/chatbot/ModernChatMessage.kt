package com.spavv.m.ui.components.chatbot


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.R
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
 fun ModernChatMessage(
    text: String,
    isBot: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isBot) Arrangement.Start else Arrangement.End
    ) {
        if (isBot) {
            AvatarSender(
                avatar = R.drawable.chatbot,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        Surface(
            modifier = Modifier
                .widthIn(max = 280.dp), // Maximum width for messages
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isBot) 4.dp else 16.dp,
                bottomEnd = if (isBot) 16.dp else 4.dp
            ),
            color = if (isBot) BackgroundItemColor else PrimaryColor.copy(alpha = 0.2f),
            elevation = 0.dp
        ) {
            androidx.compose.material3.Text(
                text = text,
                modifier = Modifier.padding(12.dp),
                color = DarkColor,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }

        if (!isBot) {
            AvatarSender(
                avatar = R.drawable.avatar,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}