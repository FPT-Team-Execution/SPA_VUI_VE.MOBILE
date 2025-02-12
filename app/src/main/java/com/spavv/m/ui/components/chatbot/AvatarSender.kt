package com.spavv.m.ui.components.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AvatarsSender(modifier: Modifier = Modifier, avatar: Int) {
    Image(
        painter = painterResource(avatar),
        contentDescription = "Avatar",
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
    )
}