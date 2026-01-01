package com.example.month4.domain.usecase

import com.example.month4.domain.repository.CartRepository

class ClearCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke() {
        repository.clearCart()
    }
}

