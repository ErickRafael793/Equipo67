package com.example.us10gestiondecarritopersonal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter (
    private var items: List<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onItemRemoved: (CartItem) -> Unit
    ) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

        class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView = view.findViewById(R.id.tvCartItemTitle)
            val tvPrice: TextView = view.findViewById(R.id.tvCartItemPrice)
            val tvQuantity: TextView = view.findViewById(R.id.tvCartItemQuantity)
            val btnMinus: Button = view.findViewById(R.id.btnCartItemMinus)
            val btnPlus: Button = view.findViewById(R.id.btnCartItemPlus)
            val btnRemove: Button = view.findViewById(R.id.btnRemoveItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cart, parent, false)
            return CartViewHolder(view)
        }

        override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
            val item = items[position]
            holder.tvTitle.text = item.title
            holder.tvPrice.text = "$${String.format("%.2f", item.price)}"
            holder.tvQuantity.text = item.quantity.toString()

            holder.btnMinus.setOnClickListener {
                onQuantityChanged(item, item.quantity - 1)
            }

            holder.btnPlus.setOnClickListener {
                onQuantityChanged(item, item.quantity + 1)
            }

            holder.btnRemove.setOnClickListener {
                onItemRemoved(item)
            }
        }

        override fun getItemCount(): Int = items.size

        fun updateData(newItems: List<CartItem>) {
            this.items = newItems
            notifyDataSetChanged()
        }
}