package com.spavv.m.ui.components.skin_test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.ui.screens.skin_test.SkinTestVM

@Composable
fun QuestionsSection(
    questions: List<SkinTestQuestion>,
    skinTestVm: SkinTestVM,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(questions) { questionItem ->
            QuestionCard(
                question = questionItem,
                skinTestVm = skinTestVm
            )
        }
    }
}