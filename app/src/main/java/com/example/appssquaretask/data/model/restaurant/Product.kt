package com.example.appssquaretask.data.model.restaurant


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Int,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("restaurant_id")
    val restaurantId: Int
)