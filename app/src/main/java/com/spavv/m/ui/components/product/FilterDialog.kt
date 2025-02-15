package com.spavv.m.ui.components.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun FilterDialog(onDismiss: () -> Unit, onApply: (String) -> Unit) {
    var filterText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lọc sản phẩm") },
        text = {
            Column {
                TextField(
                    value = filterText,
                    onValueChange = { filterText = it },
                    placeholder = { Text("Nhập từ khóa lọc") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onApply(filterText) }) {
                Text("Áp dụng")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
