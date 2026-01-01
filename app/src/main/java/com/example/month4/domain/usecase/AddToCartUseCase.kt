package com.example.month4.domain.usecase

import com.example.month4.domain.models.Product
import com.example.month4.domain.repository.CartRepository

class AddToCartUseCase(
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.addToCart(product)
    }
}