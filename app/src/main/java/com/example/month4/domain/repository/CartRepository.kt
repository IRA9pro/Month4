package com.example.month4.domain.repository

import com.example.month4.domain.models.CartItem
import com.example.month4.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)

    suspend fun clearCart()

    suspend fun checkout(): Result<String>
}