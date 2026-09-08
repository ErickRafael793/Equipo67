package com.example.us06.domain.repository

import com.example.us06.domain.model.Product

interface ProductRepository {

    suspend fun createProduct(
        product: Product
    ): Result<Product>
}