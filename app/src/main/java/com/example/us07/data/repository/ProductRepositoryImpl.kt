package com.example.us07.data.repository

import com.example.us07.data.remote.ProductApiService
import com.example.us07.domain.model.Product
import com.example.us07.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {

    override suspend fun getProductById(id: Int): Result<Product> {
        return runCatching {
            apiService.getProduct(id)
        }
    }

    override suspend fun updateProduct(id: Int, product: Product): Result<Product> {
        return runCatching {
            apiService.updateProduct(id, product)
        }
    }
}