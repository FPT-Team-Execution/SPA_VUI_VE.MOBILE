package com.spavv.m.ui.components.skin_test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.components.general.MyProcessIndicator
import com.spavv.m.ui.theme.DarkColor

@Composable
fun ProgressSection(current: Int, total: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Tiến trình khảo sát",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = DarkColor
        )
        MyProcessIndicator(currentPercent = current.toFloat() / total.toFloat())
    }
}