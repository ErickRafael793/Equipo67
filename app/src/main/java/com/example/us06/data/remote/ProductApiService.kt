package com.example.us06.data.remote

import com.example.us06.data.dto.ProductDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductApiService {

    @POST("products")
    suspend fun createProduct(
        @Body product: ProductDto
    ): ProductDto
}
