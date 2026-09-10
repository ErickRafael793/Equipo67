package com.example.us08.presentation.deleteproduct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LoginBlue = Color(0xFF3B84F1)
private val LoginWhite = Color.White
private val SuccessGreen = Color(0xFF00C853)
private val DangerRed = Color(0xFFD32F2F)

@Composable
fun DeleteProductScreen(
    viewModel: DeleteProductViewModel,
    onNavigateBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginBlue)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Detalle del producto",
            color = LoginWhite,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Escenario 3: La interfaz nunca renderiza el botón si no es Administrador
        if (uiState.isDeleteAllowed) {
            Button(
                onClick = { viewModel.onDeleteRequested() },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DangerRed,
                    contentColor = LoginWhite
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(color = LoginWhite)
                } else {
                    Text(
                        text = "Eliminar producto",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Escenario 1: Mensaje flotante / texto de éxito
        uiState.successMessage?.let { message ->
            Text(
                text = message,
                color = SuccessGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                color = Color.Yellow,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Escenario 2 & Regla de Negocio: Diálogo de confirmación obligatorio
    if (uiState.showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onCancelDelete() },
            title = {
                Text(
                    text = "Confirmar eliminación",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(text = "¿Estás seguro de eliminar este producto?")
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.onConfirmDelete() },
                    colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
                ) {
                    Text("Eliminar", color = LoginWhite)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onCancelDelete() }) {
                    Text("Cancelar")
                }
            }
        )
    }
}