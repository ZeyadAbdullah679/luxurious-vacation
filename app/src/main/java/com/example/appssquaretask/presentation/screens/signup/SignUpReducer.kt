package com.example.appssquaretask.presentation.screens.signup

import androidx.compose.runtime.Immutable
import com.example.appssquaretask.presentation.base.Reducer

class SignUpReducer :
    Reducer<SignUpReducer.State, SignUpReducer.Event, SignUpReducer.Effect> {

    @Immutable
    sealed class Event : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : Event()
        data class NameChanged(val name: String) : Event()
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        data class TermsAndConditionsChanged(val termsAndConditions: Boolean) : Event()
        data class SignUp(val email: String, val password: String) : Event()
        data object SignInClicked : Event()
        data object NavigateBackClicked : Event()
    }

    @Immutable
    sealed class Effect : Reducer.ViewEffect {
        data object NavigateToLogin : Effect()
        data object NavigateToStart : Effect()
        data class Error(val message: String) : Effect()
    }

    @Immutable
    data class State(
        val name: String,
        val email: String,
        val password: String,
        val termsAndConditions: Boolean,
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

            is Event.SignUp -> {
                previousState.copy(
                    email = event.email,
                    password = event.password
                ) to Effect.NavigateToLogin
            }

            Event.SignInClicked -> {
                previousState to Effect.NavigateToLogin
            }

            is Event.NameChanged -> {
                previousState.copy(name = event.name) to null
            }

            is Event.TermsAndConditionsChanged -> {
                previousState.copy(termsAndConditions = event.termsAndConditions) to null
            }

            Event.NavigateBackClicked -> {
                previousState to Effect.NavigateToStart
            }
        }
    }

    companion object {
        fun initial() = State(
            name = "",
            email = "",
            password = "",
            termsAndConditions = false,
            isLoading = false
        )
    }
}