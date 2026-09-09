package com.example.us07.domain.usecase

import com.example.us07.domain.model.Product
import com.example.us07.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(id: Int): Result<Product> {
        return repository.getProductById(id)
    }
}