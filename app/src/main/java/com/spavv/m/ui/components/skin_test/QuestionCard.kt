package com.spavv.m.ui.components.skin_test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.ui.screens.skin_test.SkinTestVM
import com.spavv.m.ui.theme.BackgroundItemColor
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.components.skin_test.Answer as MyAnswer

@Composable
fun QuestionCard(
    question: SkinTestQuestion,
    skinTestVm: SkinTestVM
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 2.dp,
        backgroundColor = BackgroundItemColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = question.question,
                fontSize = 16.sp,
                color = DarkColor,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                question.skinTestOptions.forEach { option ->
                    MyAnswer(
                        option = option,
                        onClick = {
                            skinTestVm.updateOrAddOption(question.questionId, option)
                        },
                        isChosen = skinTestVm.skinTestOptions.value.values.any {
                            it.optionId == option.optionId
                        }
                    )
                }
            }
        }
    }
}