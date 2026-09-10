package com.example.us10gestiondecarritopersonal

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val controller = CartViewController(CartRepositoryImpl())
    private lateinit var adapter: CartAdapter
    private lateinit var rvCartItems: RecyclerView
    private lateinit var tvCartTotal: TextView
    private lateinit var tvEmptyCart: TextView
    private lateinit var btnCheckout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCartItems = findViewById(R.id.rvCartItems)
        tvCartTotal = findViewById(R.id.tvCartTotal)
        tvEmptyCart = findViewById(R.id.tvEmptyCart)
        btnCheckout = findViewById(R.id.btnCheckout)

        setupRecyclerView()
        refreshCartUI()
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            items = controller.getCartItems(),
            onQuantityChanged = { item, newQuantity ->
                controller.updateItemQuantity(item.id, newQuantity)
                refreshCartUI()
            },
            onItemRemoved = { item ->
                controller.removeItem(item.id)
                refreshCartUI()
            }
        )
        rvCartItems.layoutManager = LinearLayoutManager(this)
        rvCartItems.adapter = adapter
    }

    private fun refreshCartUI() {
        val items = controller.getCartItems()
        if (controller.isCartEmpty()) {
            rvCartItems.visibility = View.GONE
            tvEmptyCart.visibility = View.VISIBLE
            btnCheckout.isEnabled = false
        } else {
            rvCartItems.visibility = View.VISIBLE
            tvEmptyCart.visibility = View.GONE
            btnCheckout.isEnabled = true
            adapter.updateData(items)
        }
        tvCartTotal.text = "$${String.format("%.2f", controller.calculateTotal())}"
    }
}