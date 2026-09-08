package com.example.us06.presentation.addproduct

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LoginBlue = Color(0xFF3B84F1)
private val LoginWhite = Color.White
private val SuccessGreen = Color(0xFF00C853)

@Composable
fun AddProductScreen(
    viewModel: AddProductViewModel
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
            text = "Agregar producto",
            color = LoginWhite,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        ProductTextField(
            value = uiState.title,
            onValueChange = viewModel::updateTitle,
            placeholder = "Título",
            error = uiState.titleError
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProductTextField(
            value = uiState.price,
            onValueChange = viewModel::updatePrice,
            placeholder = "Precio",
            error = uiState.priceError,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProductTextField(
            value = uiState.description,
            onValueChange = viewModel::updateDescription,
            placeholder = "Descripción",
            error = uiState.descriptionError
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProductTextField(
            value = uiState.image,
            onValueChange = viewModel::updateImage,
            placeholder = "URL de imagen",
            error = uiState.imageError,
            keyboardType = KeyboardType.Uri
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProductTextField(
            value = uiState.category,
            onValueChange = viewModel::updateCategory,
            placeholder = "Categoría",
            error = uiState.categoryError
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.createProduct()
            },
            enabled = !uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LoginWhite,
                contentColor = LoginBlue
            )
        ) {
            Text(
                text = if (uiState.isLoading) {
                    "Guardando..."
                } else {
                    "Agregar producto"
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // MENSAJE DE ÉXITO EN VERDE
        uiState.successMessage?.let {
            Text(
                text = it,
                color = SuccessGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // MENSAJE DE ERROR
        uiState.errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProductTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    error: String?,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Black
            )
        },
        textStyle = androidx.compose.ui.text.TextStyle(
            color = LoginWhite,
            fontSize = 20.sp
        ),
        singleLine = true,
        isError = error != null,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LoginWhite,
            unfocusedBorderColor = LoginWhite,
            errorBorderColor = Color.Red,
            cursorColor = LoginWhite,

            // Texto escrito por el usuario
            focusedTextColor = LoginWhite,
            unfocusedTextColor = LoginWhite,

            // Hint
            focusedPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,

            // Iconos si posteriormente agregamos alguno
            focusedLeadingIconColor = LoginWhite,
            unfocusedLeadingIconColor = LoginWhite
        ),
        supportingText = {
            if (error != null) {
                Text(
                    text = error,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }
    )
}


