package com.spavv.m.data.models

import java.util.Date

data class SkinTestQuestion(
    val questionId: String,
    val question: String,
    val questionOrder: Int? = null,
    val isActive: Boolean? = null,
    val createdAt: Date? = null,
    val skinTestOptions: List<SkinTestOption> = emptyList()
)


