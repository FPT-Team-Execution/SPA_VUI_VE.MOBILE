package com.spavv.m.ui.components.promotion

import Promotion
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor
import java.util.Date

@Composable
fun PromotionCard(
    promotion: Promotion,
    currentDate: Date
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ },
        backgroundColor = BackgroundItemColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = promotion.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkColor
                    )
                    Text(
                        text = "Mã: ${promotion.code}",
                        fontSize = 14.sp,
                        color = GreyColor,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                if(promotion.endDate != null)
                    PromotionStatus(
                        isActive = promotion.isActive ?: false,
                        endDate = promotion.endDate,
                        currentDate = currentDate
                    )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Details
            PromotionDetails(promotion = promotion)

            Spacer(modifier = Modifier.height(16.dp))

            // Footer with usage info
            PromotionFooter(promotion = promotion)
        }
    }
}
