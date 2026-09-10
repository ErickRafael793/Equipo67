package com.example.us09_aadirarticulosalcarritopersonal

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.us09_aadirarticulosalcarritopersonal.R


class MainActivity : AppCompatActivity() {

    private val controller = AddToCartController(CartRepositoryImpl())
    private val currentUserRole = UserRole.CLIENTE
    private var selectedQuantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val tvQuantity = findViewById<TextView>(R.id.tvQuantity)

        if (!controller.isAddToCartAllowed(currentUserRole)) {
            btnAddToCart.visibility = View.GONE
        }

        btnPlus.setOnClickListener {
            selectedQuantity++
            tvQuantity.text = selectedQuantity.toString()
        }

        btnMinus.setOnClickListener {
            if (selectedQuantity > 1) {
                selectedQuantity--
                tvQuantity.text = selectedQuantity.toString()
            }
        }

        btnAddToCart.setOnClickListener {
            val dummyProduct = CartItem(
                id = 1,
                title = "Mochila Fjallraven",
                price = 109.95,
                quantity = 0
            )

            val success = controller.executeAddToCart(
                product = dummyProduct,
                quantity = selectedQuantity,
                role = currentUserRole
            )

            if (success) {
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
            }
        }
    }
}