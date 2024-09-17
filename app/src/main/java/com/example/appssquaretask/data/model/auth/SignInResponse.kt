package com.example.appssquaretask.data.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    @SerialName("data")
    val userData: UserData,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("token")
    val token: String
)