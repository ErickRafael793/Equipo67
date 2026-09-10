package com.example.validar_catalogo_generaldeproductos.network

import com.example.validar_catalogo_generaldeproductos.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product
}
