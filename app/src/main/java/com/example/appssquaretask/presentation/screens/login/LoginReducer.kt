package com.example.appssquaretask.presentation.screens.login

import androidx.compose.runtime.Immutable
import com.example.appssquaretask.presentation.base.Reducer

class LoginReducer :
    Reducer<LoginReducer.State, LoginReducer.Event, LoginReducer.Effect> {

    @Immutable
    sealed class Event : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : Event()
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        data class SignIn(val email: String, val password: String) : Event()
        data object SignUpClicked : Event()
    }

    @Immutable
    sealed class Effect : Reducer.ViewEffect {
        data object NavigateToHome : Effect()
        data object NavigateToSignUp: Effect()
        data class Error(val message: String) : Effect()
    }

    @Immutable
    data class State(
        val email: String,
        val password: String,
        val isLoading: Boolean
    ) : Reducer.ViewState

    override fun reduce(
        previousState: State,
        event: Event
    ): Pair<State, Effect?> {
        return when (event) {
            is Event.UpdateLoading -> {
                previousState.copy(isLoading = event.isLoading) to null
            }

            is Event.EmailChanged -> {
                previousState.copy(email = event.email) to null
            }

            is Event.PasswordChanged -> {
                previousState.copy(password = event.password) to null
            }

            is Event.SignIn -> {
                previousState.copy(email = event.email, password = event.password) to Effect.NavigateToHome
            }

            Event.SignUpClicked -> {
                previousState to Effect.NavigateToSignUp
            }
        }
    }

    companion object{
        fun initial() = State("", "", false)
    }
}