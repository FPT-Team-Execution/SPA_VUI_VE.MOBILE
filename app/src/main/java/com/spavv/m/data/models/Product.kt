package com.spavv.m.data.models

import java.util.Date

data class Product(
    val productId: String,
    val name: String,
    val description: String,
    val price: Double,
    val categoryId: String,
    val brandId: String,
    val stockQuantity: Int?,
    val imageUrl: String,
    val ingredients: String,
    val isActive: Boolean?,
    val createdAt: Date?,
    val updatedAt: Date?,
    val brand: Brand?,
    val category: Category?,
//    val orderDetails: List<OrderDetail> = emptyList(),
//    val reviews: List<Review> = emptyList(),
//    val routineProducts: List<RoutineProduct> = emptyList(),
//    val skinTypes: List<SkinType> = emptyList()
)