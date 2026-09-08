package com.example.us06.presentation.addproduct

data class AddProductUiState(

    val title: String = "",

    val price: String = "",

    val description: String = "",

    val image: String = "",

    val category: String = "",

    val titleError: String? = null,

    val priceError: String? = null,

    val descriptionError: String? = null,

    val imageError: String? = null,

    val categoryError: String? = null,

    val isLoading: Boolean = false,

    val successMessage: String? = null,

    val errorMessage: String? = null
)