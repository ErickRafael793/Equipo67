package com.example.us10gestiondecarritopersonal

class CartViewController(private val repository: CartRepository) {

    fun getCartItems(): List<CartItem> {
        return repository.getCartItems()
    }

    fun updateItemQuantity(productId: Int, newQuantity: Int) {
        repository.updateQuantity(productId, newQuantity)
    }

    fun removeItem(productId: Int) {
        repository.removeItem(productId)
    }

    fun calculateTotal(): Double {
        return repository.getCartItems().sumOf { it.price * it.quantity }
    }

    fun isCartEmpty(): Boolean {
        return repository.getCartItems().isEmpty()
    }
}