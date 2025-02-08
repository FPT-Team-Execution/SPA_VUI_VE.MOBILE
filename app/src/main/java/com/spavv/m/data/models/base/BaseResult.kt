package com.spavv.m.data.models.base

class BaseResult<T>(
    val data: T,
    val message: String,
    val status: Int
)