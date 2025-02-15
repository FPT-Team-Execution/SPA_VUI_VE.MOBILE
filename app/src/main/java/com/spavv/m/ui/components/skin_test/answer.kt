package com.spavv.m.ui.components.skin_test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun Answer(option: SkinTestOption, onClick: () -> Unit, isChosen: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = if(isChosen) PrimaryColor else  BackgroundItemColor)
            .padding(8.dp)
            .clickable {
                onClick()
            }
    ){
        Text(
            text = option.optionText,
            color = DarkColor,
            textAlign = TextAlign.Center,
            softWrap = true,
            fontSize = 12.sp)
    }
}