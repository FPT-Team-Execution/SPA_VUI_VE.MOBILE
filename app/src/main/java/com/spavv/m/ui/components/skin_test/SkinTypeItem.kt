package com.spavv.m.ui.components.skin_test

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.data.models.SkinType
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun SkinTypeItem( skinType: SkinType) {
    val notUpdateYet : String = "Chưa cập nhật"
    Column(
        modifier = Modifier.padding(8.dp)
            .border(1.dp, color = GreyColor)
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = skinType.name,
            color = DarkColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(8.dp)

        ) {
            DetailSection(text = "Miêu tả", icon = Icons.Default.Description) {
                skinType.description ?: notUpdateYet
            }
            DetailSection(text = "Thành phần", icon = Icons.Default.Menu){
                skinType.characteristics ?: notUpdateYet
            }
            DetailSection(text = "Đề xuất", icon = Icons.Default.Check, Color.Green){
                skinType.recommendedIngredients ?: notUpdateYet
            }
            DetailSection(text = "Nên tránh", icon = Icons.Default.Close, Color.Red){
                skinType.avoidIngredients ?: notUpdateYet
            }
            DetailSection(text = "Chăm sóc da", icon = Icons.Default.Spa, PrimaryColor){
                skinType.careInstructions ?: notUpdateYet
            }
        }
    }
}

@Composable
fun DetailSection(text: String, icon: ImageVector? = null, color: Color = DarkColor, content: () -> String ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(imageVector = it, contentDescription = text, tint = color)
        }
        Text(text = text, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)

    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = content.invoke(), color = Color.Black, fontSize = 12.sp)
}