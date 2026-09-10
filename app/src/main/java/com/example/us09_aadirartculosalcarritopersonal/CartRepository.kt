package com.example.us09_aadirarticulosalcarritopersonal

object CartBus {
    val localItems = mutableListOf<CartItem>()
}

interface CartRepository {
    fun addOrUpdateProduct(item: CartItem)
}

class CartRepositoryImpl : CartRepository {
    override fun addOrUpdateProduct(item: CartItem) {
        val existing = CartBus.localItems.find { it.id == item.id }
        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            CartBus.localItems.add(item)
        }
    }
}