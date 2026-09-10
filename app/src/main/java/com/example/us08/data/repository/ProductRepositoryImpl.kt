package com.example.us08.data.repository

import com.example.us08.data.remote.ProductApiService

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {

    override suspend fun deleteProduct(id: Int): Result<Unit> {
        return try {
            val response = apiService.deleteProduct(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al eliminar el producto: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}