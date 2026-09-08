package com.example.us06.data.dto

import com.example.us06.domain.model.Product

data class ProductDto(
    val id: Int? = null,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
)

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        image = image,
        category = category
    )
}