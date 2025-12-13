package com.example.month4.data.repository

import com.example.month4.data.api.RetrofitService
import com.example.month4.data.model.ProductDto

class ProductRepository {
    suspend fun getProducts(): List<ProductDto> {
        return RetrofitService.api.getAllProducts()
    }
    suspend fun getItemProduct(id: Int): ProductDto {
        return RetrofitService.api.getProductById(id)
    }
}