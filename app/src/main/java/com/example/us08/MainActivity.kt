package com.example.us08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.us08.data.remote.RetrofitInstance
import com.example.us08.data.repository.ProductRepositoryImpl
import com.example.us08.domain.model.UserRole
import com.example.us08.domain.usecase.DeleteProductUseCase
import com.example.us08.presentation.deleteproduct.DeleteProductScreen
import com.example.us08.presentation.deleteproduct.DeleteProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Capa de Datos
        val repository = ProductRepositoryImpl(RetrofitInstance.api)

        // 2. Capa de Dominio
        val deleteProductUseCase = DeleteProductUseCase(repository)

        // 3. ViewModel Factory
        val viewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DeleteProductViewModel(
                    deleteProductUseCase = deleteProductUseCase,
                    userRole = UserRole.ADMINISTRADOR, // Para probar Escenario 3, cambia a UserRole.CLIENTE
                    productId = 1 // ID de producto para pruebas
                ) as T
            }
        }

        // 4. Interfaz de usuario
        setContent {
            val deleteViewModel: DeleteProductViewModel = viewModel(factory = viewModelFactory)
            DeleteProductScreen(viewModel = deleteViewModel)
        }
    }
}