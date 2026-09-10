package com.example.us08.presentation.deleteproduct

data class DeleteProductUiState(
    val isLoading: Boolean = false,
    val isDeleteAllowed: Boolean = false,
    val showConfirmDialog: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val isDeletedSuccessfully: Boolean = false
)