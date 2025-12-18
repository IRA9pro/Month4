package com.example.month4.data.repository

import com.example.month4.data.datasource.StoreApi
import com.example.month4.data.mappers.toDomain
import com.example.month4.domain.repository.ProductRepository

class ProductRepositoryImplementation(
    private val api: StoreApi
) : ProductRepository {
    override suspend fun getProducts() = api.getAllProducts().map { it.toDomain() }
    override suspend fun getProductById(id: Int) = api.getProductById(id).toDomain()
}