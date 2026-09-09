package com.example.us07.presentation.editproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.us07.domain.model.Product
import com.example.us07.domain.model.UserRole
import com.example.us07.domain.usecase.GetProductUseCase
import com.example.us07.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProductViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val userRole: UserRole,
    private val productId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProductUiState(productId = productId))
    val uiState: StateFlow<EditProductUiState> = _uiState.asStateFlow()

    init {
        // Criterio 3: Verificar permisos al iniciar
        if (userRole != UserRole.ADMINISTRADOR) {
            _uiState.update { it.copy(isAccessDenied = true) }
        } else {
            loadProductData()
        }
    }

    // Criterio 2: Pre-cargar información actual
    private fun loadProductData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isInitialLoading = true) }
            val result = getProductUseCase(productId)

            result.onSuccess { product ->
                _uiState.update {
                    it.copy(
                        title = product.title,
                        price = product.price.toString(),
                        description = product.description,
                        image = product.image,
                        category = product.category,
                        isInitialLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { state ->
                    state.copy(
                        errorMessage = "Error al cargar el producto",
                        isInitialLoading = false
                    )
                }
            }
        }
    }

    fun updateTitle(value: String) {
        _uiState.update { it.copy(title = value, titleError = null) }
    }

    fun updatePrice(value: String) {
        _uiState.update { it.copy(price = value, priceError = null) }
    }

    fun updateDescription(value: String) {
        _uiState.update { it.copy(description = value, descriptionError = null) }
    }

    fun updateImage(value: String) {
        _uiState.update { it.copy(image = value, imageError = null) }
    }

    fun updateCategory(value: String) {
        _uiState.update { it.copy(category = value, categoryError = null) }
    }

    fun saveProductChanges() {
        if (!validateFields()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, successMessage = null, errorMessage = null) }

            val current = _uiState.value
            val productToUpdate = Product(
                id = productId,
                title = current.title,
                price = current.price.toDoubleOrNull() ?: 0.0,
                description = current.description,
                category = current.category,
                image = current.image
            )

            val result = updateProductUseCase(productId, productToUpdate)

            result.onSuccess {
                // Criterio 1: Muestra mensaje de "Producto actualizado (Simulación)"
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        successMessage = "Producto actualizado (Simulación)"
                    )
                }
            }.onFailure { error ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Ocurrió un error al actualizar"
                    )
                }
            }
        }
    }

    // Criterio 2: Validación de campos vacíos o tipo numérico en el precio
    private fun validateFields(): Boolean {
        val state = _uiState.value
        var isValid = true

        var titleErr: String? = null
        var priceErr: String? = null
        var descErr: String? = null
        var imgErr: String? = null
        var catErr: String? = null

        if (state.title.isBlank()) {
            titleErr = "El título no puede estar vacío"
            isValid = false
        }

        if (state.price.isBlank()) {
            priceErr = "El precio no puede estar vacío"
            isValid = false
        } else if (state.price.toDoubleOrNull() == null) {
            priceErr = "El precio debe ser un número válido"
            isValid = false
        }

        if (state.description.isBlank()) {
            descErr = "La descripción no puede estar vacía"
            isValid = false
        }

        if (state.image.isBlank()) {
            imgErr = "La URL de la imagen no puede estar vacía"
            isValid = false
        }

        if (state.category.isBlank()) {
            catErr = "La categoría no puede estar vacía"
            isValid = false
        }

        _uiState.update {
            it.copy(
                titleError = titleErr,
                priceError = priceErr,
                descriptionError = descErr,
                imageError = imgErr,
                categoryError = catErr
            )
        }

        return isValid
    }
}