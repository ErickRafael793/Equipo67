package com.example.validar_catalogo_generaldeproductos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_detail)

        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val txtPrice = findViewById<TextView>(R.id.txtPrice)
        val txtCategory = findViewById<TextView>(R.id.txtCategory)
        val txtDescription = findViewById<TextView>(R.id.txtDescription)
        val txtRole = findViewById<TextView>(R.id.txtRole)

        val title = intent.getStringExtra("PRODUCT_TITLE")
        val price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val category = intent.getStringExtra("PRODUCT_CATEGORY")
        val description = intent.getStringExtra("PRODUCT_DESCRIPTION")

        txtTitle.text = title
        txtPrice.text = "Precio: $price"
        txtCategory.text = "Categoría: $category"
        txtDescription.text = "Descripción: $description"
        txtRole.text = "Rol: CLIENTE"
    }
}