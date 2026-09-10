package com.example.us10gestiondecarritopersonal

import android.util.Log

interface CartRepository {
    fun getCartItems(): List<CartItem>
    fun updateQuantity(productId: Int, newQuantity: Int)
    fun removeItem(productId: Int)
    fun clearCart()
}

class CartRepositoryImpl : CartRepository {
    override fun getCartItems(): List<CartItem> = CartBus.localItems

    override fun updateQuantity(productId: Int, newQuantity: Int) {
        val item = CartBus.localItems.find { it.id == productId }
        if (item != null) {
            if (newQuantity <= 0) {
                removeItem(productId)
            } else {
                item.quantity = newQuantity
                Log.d("API_HTTP", "PUT /api/cart/items/${productId} -> Cantidad actualizada: $newQuantity")
            }
        }
    }

    override fun removeItem(productId: Int) {
        val removed = CartBus.localItems.removeAll { it.id == productId }
        if (removed) {
            Log.d("API_HTTP", "DELETE /api/cart/items/${productId} -> Producto eliminado")
        }
    }

    override fun clearCart() {
        CartBus.localItems.clear()
        Log.d("API_HTTP", "DELETE /api/cart -> Carrito vaciado")
    }
}