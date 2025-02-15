package com.spavv.m.data.models.base

class Paginate<T> (
    val items : List<T>,
    val page: Int,
    val size: Int,
    val total: Int,
    val totalPages: Int
)