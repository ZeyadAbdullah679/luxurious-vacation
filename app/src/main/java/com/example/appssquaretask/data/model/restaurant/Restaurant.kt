package com.example.appssquaretask.data.model.restaurant


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    @SerialName("address")
    val address: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("products")
    val products: List<Product>,
    @SerialName("restaurant_lat")
    val restaurantLat: String,
    @SerialName("restaurant_long")
    val restaurantLong: String
)