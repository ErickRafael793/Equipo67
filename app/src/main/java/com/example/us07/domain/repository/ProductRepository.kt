package com.example.us07.domain.repository

import com.example.us07.domain.model.Product

interface ProductRepository {
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun updateProduct(id: Int, product: Product): Result<Product>
}