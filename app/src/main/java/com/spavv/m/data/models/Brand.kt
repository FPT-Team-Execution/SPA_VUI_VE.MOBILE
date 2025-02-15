package com.spavv.m.data.models

import java.util.Date

data class Brand(
    val brandId: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val createdAt: Date,
    val updatedAt: Date?,
    val status: String,
    val products: List<Product> = emptyList()
)