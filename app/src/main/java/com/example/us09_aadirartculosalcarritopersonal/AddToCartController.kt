package com.example.us09_aadirarticulosalcarritopersonal

class AddToCartController(private val repository: CartRepository) {

    fun isAddToCartAllowed(role: UserRole): Boolean {
        return role != UserRole.AUDITOR
    }

    fun executeAddToCart(product: CartItem, quantity: Int, role: UserRole): Boolean {
        if (!isAddToCartAllowed(role) || quantity <= 0) {
            return false
        }

        val itemToAdd = product.copy(quantity = quantity)
        repository.addOrUpdateProduct(itemToAdd)
        return true
    }
}