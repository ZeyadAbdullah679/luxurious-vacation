package com.example.appssquaretask.presentation.screens.login

import androidx.compose.runtime.Immutable
import com.example.appssquaretask.presentation.base.Reducer

class LoginReducer :
    Reducer<LoginReducer.LoginState, LoginReducer.LoginEvent, LoginReducer.LoginEffect> {

    @Immutable
    sealed class LoginEvent : Reducer.ViewEvent {
        data class EmailChanged(val email: String) : LoginEvent()
        data class PasswordChanged(val password: String) : LoginEvent()
        data class SignIn(val email: String, val password: String) : LoginEvent()
    }

    @Immutable
    sealed class LoginEffect : Reducer.ViewEffect {
        data object NavigateToHome : LoginEffect()
    }

    @Immutable
    data class LoginState(
        val email: String,
        val password: String,
    ) : Reducer.ViewState


    override fun reduce(
        previousState: LoginState,
        event: LoginEvent
    ): Pair<LoginState, LoginEffect?> {
        return when (event) {
            is LoginEvent.EmailChanged -> {
                previousState.copy(email = event.email) to null
            }
            is LoginEvent.PasswordChanged -> {
                previousState.copy(password = event.password) to null
            }
            is LoginEvent.SignIn -> {
                previousState.copy(email = event.email, password = event.password) to LoginEffect.NavigateToHome
            }
        }
    }

}