package com.example.appssquaretask.data.model.city


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Restaurants(
    @SerialName("count")
    val count: Int,
    @SerialName("data")
    val data: List<Restaurant>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)