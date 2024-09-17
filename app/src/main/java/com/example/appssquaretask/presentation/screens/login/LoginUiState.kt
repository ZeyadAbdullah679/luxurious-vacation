package com.example.appssquaretask.presentation.screens.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiState(
    val email: String,
    val password: String,
)
