package com.example.us08.presentation.deleteproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.us08.domain.model.UserRole
import com.example.us08.domain.usecase.DeleteProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeleteProductViewModel(
    private val deleteProductUseCase: DeleteProductUseCase,
    private val userRole: UserRole,
    private val productId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeleteProductUiState())
    val uiState: StateFlow<DeleteProductUiState> = _uiState.asStateFlow()

    init {
        // Escenario 3: Verificar permisos para renderizar o no el botón
        _uiState.update { it.copy(isDeleteAllowed = userRole == UserRole.ADMINISTRADOR) }
    }

    // Escenario 2: Abrir diálogo de confirmación
    fun onDeleteRequested() {
        if (_uiState.value.isDeleteAllowed) {
            _uiState.update { it.copy(showConfirmDialog = true) }
        }
    }

    // Escenario 2: Cancelar el borrado
    fun onCancelDelete() {
        _uiState.update { it.copy(showConfirmDialog = false) }
    }

    // Escenario 1: Confirmación y consumo de la API DELETE
    fun onConfirmDelete() {
        _uiState.update { it.copy(showConfirmDialog = false, isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = deleteProductUseCase(productId, userRole)
            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = "Producto eliminado exitosamente del catálogo.",
                            isDeletedSuccessfully = true
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Ocurrió un error inesperado."
                        )
                    }
                }
            )
        }
    }
}