package com.example.month4.domain.usecase

import com.example.month4.domain.repository.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int) = repository.getProductById(id)
}