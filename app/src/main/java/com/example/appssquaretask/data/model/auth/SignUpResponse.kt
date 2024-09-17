package com.example.appssquaretask.data.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("data")
    val userData: UserData,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)