package com.example.month4.domain.repository

import com.example.month4.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}