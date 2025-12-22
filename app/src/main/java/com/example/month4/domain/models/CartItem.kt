package com.example.month4.domain.models

data class CartItem (
    val product: Product,
    val quantity: Int = 1
)