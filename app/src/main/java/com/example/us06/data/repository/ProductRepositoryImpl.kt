package com.example.us06.data.repository

import com.example.us06.data.dto.ProductDto
import com.example.us06.data.dto.toDomain
import com.example.us06.data.remote.ProductApiService
import com.example.us06.domain.model.Product
import com.example.us06.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: ProductApiService
) : ProductRepository {

    override suspend fun createProduct(
        product: Product
    ): Result<Product> {

        return try {

            val response = api.createProduct(
                ProductDto(
                    title = product.title,
                    price = product.price,
                    description = product.description,
                    image = product.image,
                    category = product.category
                )
            )

            Result.success(response.toDomain())

        } catch (exception: Exception) {

            Result.failure(exception)
        }
    }
}