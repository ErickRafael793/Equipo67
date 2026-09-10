package com.example.validar_catalogo_generaldeproductos

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.validar_catalogo_generaldeproductos.adapter.ProductAdapter
import com.example.validar_catalogo_generaldeproductos.model.Product
import com.example.validar_catalogo_generaldeproductos.network.RetrofitClient
import kotlinx.coroutines.launch
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerProducts: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerProducts = findViewById(R.id.recyclerProducts)
        progressBar = findViewById(R.id.progressBar)

        val btnTodos = findViewById<Button>(R.id.btnTodos)
        val btnElectronics = findViewById<Button>(R.id.btnElectronics)
        val btnJewelery = findViewById<Button>(R.id.btnJewelery)
        val btnMensClothing = findViewById<Button>(R.id.btnMensClothing)
        val btnWomensClothing = findViewById<Button>(R.id.btnWomensClothing)

        adapter = ProductAdapter(emptyList()) { product ->

            val intent = Intent(this, ProductDetailActivity::class.java)

            intent.putExtra("PRODUCT_ID", product.id)
            intent.putExtra("PRODUCT_TITLE", product.title)
            intent.putExtra("PRODUCT_PRICE", product.price)
            intent.putExtra("PRODUCT_CATEGORY", product.category)
            intent.putExtra("PRODUCT_DESCRIPTION", product.description)
            intent.putExtra("PRODUCT_IMAGE", product.image)

            startActivity(intent)
        }

        recyclerProducts.layoutManager = LinearLayoutManager(this)
        recyclerProducts.adapter = adapter

        btnTodos.setOnClickListener {
            cargarProductos()
        }

        btnElectronics.setOnClickListener {
            cargarPorCategoria("electronics")
        }

        btnJewelery.setOnClickListener {
            cargarPorCategoria("jewelery")
        }

        btnMensClothing.setOnClickListener {
            cargarPorCategoria("men's clothing")
        }

        btnWomensClothing.setOnClickListener {
            cargarPorCategoria("women's clothing")
        }

        cargarProductos()
    }

    private fun cargarProductos() {

        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {

            try {

                val products: List<Product> =
                    RetrofitClient.api.getProducts()

                adapter.updateProducts(products)

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Error al cargar los productos",
                    Toast.LENGTH_LONG
                ).show()

            } finally {

                progressBar.visibility = View.GONE
            }
        }
    }

    private fun cargarPorCategoria(categoria: String) {

        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {

            try {

                val products: List<Product> =
                    RetrofitClient.api.getProductsByCategory(categoria)

                adapter.updateProducts(products)

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Error al cargar la categoría",
                    Toast.LENGTH_LONG
                ).show()

            } finally {

                progressBar.visibility = View.GONE
            }
        }
    }
}