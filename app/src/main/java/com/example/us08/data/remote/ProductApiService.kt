package com.example.us08.data.remote

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ProductApiService {
    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Unit>
}