package com.example.us08.data.repository

interface ProductRepository {
    suspend fun deleteProduct(id: Int): Result<Unit>
}