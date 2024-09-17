package com.example.appssquaretask.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.example.appssquaretask.domain.repository.AuthRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel()  {

    suspend fun signIn(email: String, password: String) =
        authRepository.signIn(email, password)
}