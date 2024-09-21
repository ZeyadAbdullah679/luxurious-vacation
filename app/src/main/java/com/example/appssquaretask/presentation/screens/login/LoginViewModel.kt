package com.example.appssquaretask.presentation.screens.login

import com.example.appssquaretask.domain.repository.AuthRepository
import com.example.appssquaretask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel<LoginReducer.LoginState, LoginReducer.LoginEvent, LoginReducer.LoginEffect>(
    LoginReducer.LoginState("", ""),
    LoginReducer()
) {

}