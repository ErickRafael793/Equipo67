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

// Modelo mínimo para representar un usuario registrado.
data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String
)

// Datos locales de ejemplo.
// Cuando el equipo conecte una API o base de datos, esta lista se reemplaza por los datos reales.
private val usuariosRegistrados = listOf(
    Usuario(1, "Erick Rafael", "erick@correo.com"),
    Usuario(2, "Kurt Cobain", "kurt@correo.com"),
    Usuario(3, "Usuario Demo", "demo@correo.com")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SolucionbaseTheme {
                PantallaUsuarios()
            }
        }
    }
}

@Composable
fun PantallaUsuarios() {
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
                text = "Usuarios registrados",
                style = MaterialTheme.typography.headlineSmall
            )

            // Lista que muestra todos los usuarios disponibles.
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(usuariosRegistrados) { usuario ->
                    UsuarioItem(usuario)
                }
            }
        }
    }
}

@Composable
fun UsuarioItem(usuario: Usuario) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Se muestran únicamente los datos básicos necesarios del usuario.
            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "ID: ${usuario.id}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = usuario.correo,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
