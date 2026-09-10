package com.example.us08.domain.usecase

import com.example.us08.data.repository.ProductRepository
import com.example.us08.domain.model.UserRole

class DeleteProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int, userRole: UserRole): Result<Unit> {
        // Escenario 3: Restricción de permisos y seguridad a nivel de código
        if (userRole != UserRole.ADMINISTRADOR) {
            return Result.failure(IllegalAccessException("Acceso denegado: Solo el Administrador puede eliminar productos."))
        }
        return repository.deleteProduct(productId)
    }
}