package com.spavv.m.ui.screens.chatbot

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.components.chatbot.ChatInput
import com.spavv.m.ui.components.chatbot.ModernChatMessage
import com.spavv.m.ui.components.general.ModernTopAppBar
import com.spavv.m.ui.theme.BackgroundColor
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

    // Welcome message
    LaunchedEffect(Unit) {
        chatVM.addMessage(
            ChatMessageData(
                "Chào bạn, tôi là SpaBot. Hôm nay bạn cần tư vấn gì?",
                isBot = true
            )
        )
    }

    // Auto scroll to bottom
    LaunchedEffect(chatVM.chatMessages.value.size) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tư vấn",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
                },
                backgroundColor = BackgroundColor,
                elevation = 0.dp,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                modifier = Modifier.shadow(elevation = 4.dp)
            )
        },
        backgroundColor = BackgroundColor,
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            // Chat Messages
            ChatMessages(
                messages = chatVM.chatMessages.value,
                scrollState = scrollState,
                modifier = Modifier.weight(1f)
            )

            // Input Area
            ChatInput(
                onSendMessage = { message ->
                    chatVM.sendMessage(message)
                    keyboardController?.hide()
                }
            )
        }
    }
}


@Composable
private fun ChatMessages(
    messages: List<ChatMessageData>,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        messages.forEach { message ->
            ModernChatMessage(
                text = message.text,
                isBot = message.isBot
            )
        }
    }
}









