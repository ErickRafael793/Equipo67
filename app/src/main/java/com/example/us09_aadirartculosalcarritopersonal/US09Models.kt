package com.example.us09_aadirarticulosalcarritopersonal

enum class UserRole {
    CLIENTE,
    AUDITOR,
    ADMINISTRADOR
}

data class CartItem(
    val id: Int,
    val title: String,
    val price: Double,
    var quantity: Int
)