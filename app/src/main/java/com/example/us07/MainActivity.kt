package com.example.us07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.us07.data.remote.ProductApiService
import com.example.us07.data.repository.ProductRepositoryImpl
import com.example.us07.domain.model.UserRole
import com.example.us07.domain.usecase.GetProductUseCase
import com.example.us07.domain.usecase.UpdateProductUseCase
import com.example.us07.presentation.editproduct.EditProductScreen
import com.example.us07.presentation.editproduct.EditProductViewModel
import com.example.us07.ui.theme.US07Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Instanciación de la capa de datos (Retrofit y Repositorio)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ProductApiService::class.java)
        val repository = ProductRepositoryImpl(apiService)

        // 2. Instanciación de Casos de Uso (Capa de Dominio)
        val getProductUseCase = GetProductUseCase(repository)
        val updateProductUseCase = UpdateProductUseCase(repository)

        // 3. Factory para inyectar dependencias al ViewModel sin librerías pesadas de DI
        val viewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return EditProductViewModel(
                    getProductUseCase = getProductUseCase,
                    updateProductUseCase = updateProductUseCase,
                    userRole = UserRole.ADMINISTRADOR, // Cambiar a CLIENTE o AUDITOR para probar denegación (Escenario 3)
                    productId = 1 // ID del producto a cargar/editar
                ) as T
            }
        }

        // 4. Renderizado de la UI con Jetpack Compose
        setContent {
            US07Theme {
                val editViewModel: EditProductViewModel = viewModel(factory = viewModelFactory)
                EditProductScreen(viewModel = editViewModel)
            }
        }
    }
}