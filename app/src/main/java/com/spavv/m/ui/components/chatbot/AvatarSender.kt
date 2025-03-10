package com.spavv.m.ui.components.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AvatarSender(
    avatar: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(36.dp),
        shape = CircleShape,
        elevation = 2.dp
    ) {
        Image(
            painter = painterResource(avatar),
            contentDescription = "Avatar",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}