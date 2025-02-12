package com.spavv.m.ui.screens.chatbot

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.chatbot.ChatInput
import com.spavv.m.ui.components.chatbot.ChatMessage
import com.spavv.m.ui.screens.skin_test.SkinTestVM
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavigation.current
    val scrollState = rememberScrollState()
    val chatVM = viewModel<ChatVM>(
        factory = viewModelFactory { ChatVM(MyApp.appModule.chatDataSource) }
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit) {
        //Add bot welcome bot message
        val welcomeChat = "Chào bạn, tôi là SpaBot. Hôm nay bạn cần tư vấn gì?"
        chatVM.addMessage(ChatMessageData(welcomeChat, isBot = true))

    }
    // Tự động cuộn xuống cuối khi danh sách tin nhắn thay đổi
    LaunchedEffect(chatVM.chatMessages.value.size) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tư vấn - Spa Bot") },
                backgroundColor = PrimaryColor,
                navigationIcon = {
                    val canGoBack = navController.previousBackStackEntry != null
                    if (canGoBack) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )
        },
        modifier = modifier
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                chatVM.chatMessages.value.forEach { message ->
                    ChatMessage(text = message.text, isBot = message.isBot)
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp
            ) {
                ChatInput { message ->
                    chatVM.sendMessage(message)
                    keyboardController?.hide() // Ẩn bàn phím sau khi gửi tin nhắn
                }
            }
        }
    }
}



