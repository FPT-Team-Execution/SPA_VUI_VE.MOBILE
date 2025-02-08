package com.spavv.m.data.models

import java.util.Date

data class Category(
    val categoryId: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isActive: Boolean?,
    val parentCategoryId: String?,
    val displayOrder: Int?,
    val createdAt: Date?,
    val updatedAt: Date?,
    val parentCategory: Category?,
    val inverseParentCategory: List<Category> = emptyList(),
    val products: List<Product> = emptyList()
)