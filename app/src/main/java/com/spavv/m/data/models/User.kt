package com.spavv.m.data.models

import java.util.Date

data class User(
    val userId: String,
    val username: String,
    val email: String,
    val fullname: String,
    val phoneNumber: String?,
    val address: String?,
    val profileImageUrl: String?,
    val isActive: Boolean?,
    val createdAt: Date?,
    val updatedAt: Date?
)