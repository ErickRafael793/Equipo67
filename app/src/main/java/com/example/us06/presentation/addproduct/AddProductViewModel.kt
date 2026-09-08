package com.example.us06.presentation.addproduct

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.us06.domain.model.Product
import com.example.us06.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(AddProductUiState())

    val uiState: StateFlow<AddProductUiState> =
        _uiState.asStateFlow()


    fun updateTitle(value: String) {

        _uiState.value =
            _uiState.value.copy(
                title = value,
                titleError = null
            )
    }


    fun updatePrice(value: String) {

        _uiState.value =
            _uiState.value.copy(
                price = value,
                priceError = null
            )
    }


    fun updateDescription(value: String) {

        _uiState.value =
            _uiState.value.copy(
                description = value,
                descriptionError = null
            )
    }


    fun updateImage(value: String) {

        _uiState.value =
            _uiState.value.copy(
                image = value,
                imageError = null
            )
    }


    fun updateCategory(value: String) {

        _uiState.value =
            _uiState.value.copy(
                category = value,
                categoryError = null
            )
    }


    fun createProduct() {

        val state = _uiState.value

        val titleError =
            if (state.title.isBlank())
                "El título es obligatorio"
            else null

        val priceError = when {

            state.price.isBlank() ->
                "El precio es obligatorio"

            state.price.toDoubleOrNull() == null ->
                "El precio debe ser numérico"

            state.price.toDouble() < 0 ->
                "El precio no puede ser negativo"

            else -> null
        }

        val descriptionError =
            if (state.description.isBlank())
                "La descripción es obligatoria"
            else null

        val imageError = when {

            state.image.isBlank() ->
                "La URL de imagen es obligatoria"

            !Patterns.WEB_URL
                .matcher(state.image)
                .matches() ->
                "Ingresa una URL válida"

            else -> null
        }

        val categoryError =
            if (state.category.isBlank())
                "La categoría es obligatoria"
            else null


        val hasErrors =
            titleError != null ||
                    priceError != null ||
                    descriptionError != null ||
                    imageError != null ||
                    categoryError != null


        if (hasErrors) {

            _uiState.value =
                state.copy(
                    titleError = titleError,
                    priceError = priceError,
                    descriptionError = descriptionError,
                    imageError = imageError,
                    categoryError = categoryError
                )

            return
        }


        val product = Product(

            title = state.title,

            price = state.price.toDouble(),

            description = state.description,

            image = state.image,

            category = state.category
        )


        viewModelScope.launch {

            _uiState.value =
                state.copy(
                    isLoading = true,
                    errorMessage = null,
                    successMessage = null
                )


            repository
                .createProduct(product)
                .onSuccess { createdProduct ->

                    _uiState.value =
                        AddProductUiState(

                            successMessage =
                                "Producto creado correctamente. " +
                                        "ID generado: ${createdProduct.id}"
                        )
                }
                .onFailure {

                    _uiState.value =
                        _uiState.value.copy(

                            isLoading = false,

                            errorMessage =
                                "No se pudo crear el producto."
                        )
                }
        }
    }
}