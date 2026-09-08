package com.example.us06.domain.model
data class Product(
    val id: Int? = null,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
)
