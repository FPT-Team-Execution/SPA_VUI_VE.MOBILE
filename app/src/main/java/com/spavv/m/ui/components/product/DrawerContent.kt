package com.spavv.m.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Category
import com.spavv.m.ui.theme.PrimaryColor
import com.spavv.m.ui.theme.DarkColor

@Composable
fun DrawerContent(
    categories: List<Category>?,
    brands: List<Brand>?,
    onClose: () -> Unit,
    onSelectCategory: (String) -> Unit,
    onSelectBrand: (String) -> Unit,
    selectedCategory: String = "",
    selectedBrand: String = ""
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 8.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Bộ lọc",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkColor,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }
            }

            Divider(color = Color(0xFFEEEEEE))

            // Content area
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Categories section
                DrawerSectionTitle("Danh mục sản phẩm")

                Spacer(modifier = Modifier.height(8.dp))

                DrawerFilterItem(
                    text = "Tất cả sản phẩm",
                    isSelected = selectedCategory.isEmpty(),
                    onClick = { onSelectCategory("") }
                )

                categories?.forEach { category ->
                    DrawerFilterItem(
                        text = category.name,
                        isSelected = selectedCategory == category.name,
                        onClick = { onSelectCategory(category.name) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Brands section
                DrawerSectionTitle("Thương hiệu")

                Spacer(modifier = Modifier.height(8.dp))

                DrawerFilterItem(
                    text = "Tất cả thương hiệu",
                    isSelected = selectedBrand.isEmpty(),
                    onClick = { onSelectBrand("") }
                )

                brands?.forEach { brand ->
                    DrawerFilterItem(
                        text = brand.name,
                        isSelected = selectedBrand == brand.name,
                        onClick = { onSelectBrand(brand.name) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Footer with action buttons
            Divider(color = Color(0xFFEEEEEE))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {
                        onSelectCategory("")
                        onSelectBrand("")
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Gray
                    ),
                    border = ButtonDefaults.outlinedBorder.copy(),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Xóa tất cả")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Áp dụng")
                }
            }
        }
    }
}

@Composable
fun DrawerSectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = DarkColor
    )
}

@Composable
fun DrawerFilterItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) PrimaryColor.copy(alpha = 0.1f) else Color.Transparent
    val textColor = if (isSelected) PrimaryColor else Color.DarkGray

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        color = backgroundColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}