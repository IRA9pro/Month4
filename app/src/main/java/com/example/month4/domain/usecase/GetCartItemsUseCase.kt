package com.example.month4.domain.usecase

import com.example.month4.domain.repository.CartRepository

class GetCartItemsUseCase(
    private val repository: CartRepository
) {
    operator fun invoke() = repository.cartItems
}