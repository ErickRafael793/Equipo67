package com.example.us07.presentation.editproduct

data class EditProductUiState(
    val productId: Int = 0,
    val title: String = "",
    val price: String = "",
    val description: String = "",
    val image: String = "",
    val category: String = "",

    // Errores de validación
    val titleError: String? = null,
    val priceError: String? = null,
    val descriptionError: String? = null,
    val imageError: String? = null,
    val categoryError: String? = null,

    // Estados de carga y mensajes
    val isInitialLoading: Boolean = false,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val isAccessDenied: Boolean = false
)