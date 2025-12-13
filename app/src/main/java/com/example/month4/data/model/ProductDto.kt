package com.example.month4.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("id")
    val id: Int?,
    @SerialName("category")
    val category: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("rating")
    val rating: Rating?,
    @SerialName("title")
    val title: String?
)