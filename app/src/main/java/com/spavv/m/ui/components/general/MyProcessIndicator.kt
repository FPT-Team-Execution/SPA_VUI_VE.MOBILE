package com.spavv.m.ui.components.general

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun MyProcessIndicator(
    strokeCap: StrokeCap = StrokeCap.Round,
    color: Color = PrimaryColor,
    currentPercent: Float,
    backgroundColor: Color = GreyColor,
    height: Double = 8.0
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        LinearProgressIndicator(

            progress = currentPercent,
            color = color,
            strokeCap = strokeCap,
            backgroundColor = backgroundColor,
            modifier = Modifier.height(height.dp)
        )
    }
}


//@Preview(widthDp = 375, heightDp = 812)
//@Composable
//private fun TestPageSuccessPreview() {
//    MyProcessIndicator(currentPercent = 0.2f)
//}