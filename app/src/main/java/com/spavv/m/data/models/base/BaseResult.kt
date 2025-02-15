package com.spavv.m.data.models.base

import com.google.gson.annotations.Expose

class BaseResult<T>(
    @Expose
    val data: T,
    @Expose
    val message: String,
    @Expose
    val status: Int
)