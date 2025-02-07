package com.spavv.m.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Slider(images: List<String>, autoScroll: Boolean = true) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        while (autoScroll) {
            delay(3000) // Đổi ảnh sau 3 giây
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            count = images.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(images[page]),
                contentDescription = "Slider Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
    }
}

@Composable
fun PreviewSlider() {
    Slider(
        images = listOf(
            "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2023/05/skincare-la-gi-jpg-1685324156-29052023083556.jpg",
            "https://aladin.com.vn/media/news/1111_mochi-skincare-01.jpg",
            "https://img.vuahanghieu.com/unsafe/0x0/left/top/smart/filters:quality(90)/https://admin.vuahanghieu.com/upload/news/content/2022/06/skincare-la-gi-jpg-1654847129-10062022144529.jpg"
        )
    )
}