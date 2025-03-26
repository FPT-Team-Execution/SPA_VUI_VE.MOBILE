package com.spavv.m.ui.components.promotion

import Promotion
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.spavv.m.ui.screens.promotion.formatDateDisplay
import com.spavv.m.ui.screens.promotion.formatPrice
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PromotionDetails(promotion: Promotion) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailRow(
            icon = Icons.Default.LocalOffer,
            label = "Giảm giá",
            value = "$${promotion.discountAmount.formatPrice()}"
        )


            DetailRow(
                icon = Icons.Default.ShoppingCart,
                label = "Đơn tối thiểu",
                value = "$${promotion.minimumPurchase?.formatPrice() ?: 0.0}"
            )


        DetailRow(
            icon = Icons.Default.DateRange,
            label = "Thời gian",
            value = "${if(promotion.startDate != null) promotion.startDate.formatDateDisplay() else ""} - ${
                if (promotion.endDate != null) promotion.endDate.formatDateDisplay() else ""
            }"
        )


//        if (promotion.usageLimit != null) {
//            val usagesLeft = promotion.usageLimit - (promotion.promotionUsages?.size ?: 0)
//            com.spavv.m.ui.screens.promotion.DetailRow(
//                icon = Icons.Default.RepeatOne,
//                label = "Lượt sử dụng còn lại",
//                value = "$usagesLeft"
//            )
//        }
    }

}