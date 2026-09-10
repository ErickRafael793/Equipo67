package com.example.us10gestiondecarritopersonal

    enum class UserRole {
        CLIENTE,
        AUDITOR
    }

    data class CartItem(
        val id: Int,
        val title: String,
        val price: Double,
        var quantity: Int
    )

    object CartBus {
        val localItems = mutableListOf<CartItem>(
            CartItem(1, "Mochila Fjallraven", 109.95, 2),
            CartItem(2, "Playera Mens Casual", 22.30, 1)
        )
    }