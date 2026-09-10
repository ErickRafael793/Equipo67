package com.example.solucionbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.solucionbase.ui.theme.SolucionbaseTheme

// Modelo mínimo para representar un carrito dentro del historial global.
data class CarritoHistorico(
    val id: Int,
    val usuario: String,
    val productos: Int,
    val total: Double
)

// Datos locales de ejemplo.
// Después se pueden sustituir por la información real de la API o base de datos.
private val historialCarritos = listOf(
    CarritoHistorico(1, "Erick Rafael", 3, 1250.00),
    CarritoHistorico(2, "Kurt Cobain", 2, 780.50),
    CarritoHistorico(3, "Usuario Demo", 5, 2100.00)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SolucionbaseTheme {
                PantallaHistorialCarritos()
            }
        }
    }
}

@Composable
fun PantallaHistorialCarritos() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Título de la historia de usuario.
            Text(
                text = "Histórico global de carritos",
                style = MaterialTheme.typography.headlineSmall
            )

            // Lista con los carritos registrados de distintos usuarios.
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historialCarritos) { carrito ->
                    CarritoItem(carrito)
                }
            }
        }
    }
}

@Composable
fun CarritoItem(carrito: CarritoHistorico) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Se muestran únicamente los datos básicos del historial.
            Text(
                text = "Carrito #${carrito.id}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Usuario: ${carrito.usuario}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Productos: ${carrito.productos}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Total: $${"%.2f".format(carrito.total)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
