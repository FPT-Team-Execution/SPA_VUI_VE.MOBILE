package com.spavv.m.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.spavv.m.di.MyApp
import com.spavv.m.helper.viewModelFactory
import com.spavv.m.ui.theme.DarkColor

@Composable
fun ProfileDetailScreen(modifier: Modifier = Modifier, userId: String, navController: NavController) {
    val profileVM: ProfileVM = viewModel(
        factory = viewModelFactory {
            ProfileVM(
                MyApp.appModule.userDataSource
            )
        }
    )

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(userId) {
        profileVM.fetchUser(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chi tiết tài khoản",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Back",
                                tint = DarkColor
                            )
                        }
                    }
                },
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.White, Color.White.copy(alpha = 0.95f))
                        )
                    )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(
                    top = innerPaddings.calculateTopPadding(),
                    bottom = innerPaddings.calculateBottomPadding(),
                    start = 0.dp,
                    end = 0.dp
                ))
                .verticalScroll(scrollState)
        ) {
            // User profile image with overlay gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xFFF5F5F5))
            ) {
                AsyncImage(
                    model = profileVM.user.value?.profileImageUrl,
                    contentDescription = "User Profile Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // User details card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // User name
                    profileVM.user.value?.let { user ->
                        Text(
                            text = user.fullname,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkColor
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email and phone number with icons
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "  ${profileVM.user.value?.email ?: "Unknown email"}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Phone",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "  ${profileVM.user.value?.phoneNumber ?: "Unknown phone"}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Divider
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Address section
                    Text(
                        text = "Địa chỉ:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    profileVM.user.value?.let { user ->
                        Text(
                            text = user.address ?: "Unknown address",
                            textAlign = TextAlign.Justify,
                            lineHeight = 24.sp,
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Extra spacing at bottom to ensure content isn't hidden by bottom bar
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}