package com.spavv.m.data.models

import java.util.Date

data class SkinType(
    val skinTypeId: String = "",
    val name: String = "",
    val description: String? = "",
    val characteristics: String? = "",
    val recommendedIngredients: String? = "",
    val avoidIngredients: String? = "",
    val careInstructions: String? = "",
    val isActive: Boolean? = false,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
)
