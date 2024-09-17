package com.example.appssquaretask.presentation.screens.login

sealed class LoginIntent {
    data class SignIn(val email: String, val password: String) : LoginIntent()
}