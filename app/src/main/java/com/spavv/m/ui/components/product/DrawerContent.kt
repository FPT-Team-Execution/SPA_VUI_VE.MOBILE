package com.spavv.m.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spavv.m.data.models.Category
import com.spavv.m.ui.components.home.SectionTitle

@Composable
fun DrawerContent(categories: List<Category>? ,onClose: () -> Unit, onSelectCategory: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .background(Color.White)
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Column {

            SectionTitle("Danh mục")

            TextButton(
                onClick = { onSelectCategory("") },
            ) {
                Text(
                    text = "Tất cả",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            categories?.forEach { category ->
                TextButton(
                    onClick = { onSelectCategory(category.name) },
                ) {
                    Text(
                        text = category.name,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onClose, modifier = Modifier.align(Alignment.End)) {
            Text("Đóng")
        }
    }
}
