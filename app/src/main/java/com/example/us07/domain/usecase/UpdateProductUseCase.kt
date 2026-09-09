package com.example.us07.domain.usecase

import com.example.us07.domain.model.Product
import com.example.us07.domain.repository.ProductRepository

class UpdateProductUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(id: Int, product: Product): Result<Product> {
        return repository.updateProduct(id, product)
    }
}