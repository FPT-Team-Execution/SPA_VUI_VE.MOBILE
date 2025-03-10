package com.spavv.m.ui.components.promotion

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.theme.GreyColor
import java.util.Date

@Composable
fun PromotionStatus(
    isActive: Boolean,
    endDate: Date,
    currentDate: Date
) {
    val isExpired = currentDate.after(endDate)

    val (backgroundColor, textColor, text) = when {
        !isActive -> Triple(
            GreyColor.copy(alpha = 0.2f),
            GreyColor,
            "Không khả dụng"
        )
        isExpired -> Triple(
            Color.Red.copy(alpha = 0.2f),
            Color.Red,
            "Hết hạn"
        )
        else -> Triple(
            Color.Green.copy(alpha = 0.2f),
            Color.Green,
            "Đang áp dụng"
        )
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}