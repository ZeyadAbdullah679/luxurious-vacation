package com.example.appssquaretask.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.example.appssquaretask.domain.DataState
import com.example.appssquaretask.domain.repository.AuthRepository
import com.example.appssquaretask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<
        LoginReducer.State,
        LoginReducer.Event,
        LoginReducer.Effect
        >(
    initialState = LoginReducer.initial(),
    reducer = LoginReducer()
) {
    fun login(email: String, password: String) {
        viewModelScope.launch(IO) {
            sendEvent(LoginReducer.Event.UpdateLoading(true))

            authRepository.signIn(email, password).collect { result ->
                when (result) {
                    DataState.Empty -> Unit
                    is DataState.Error -> {
                        sendEvent(LoginReducer.Event.UpdateLoading(false))
                        sendEffect(LoginReducer.Effect.Error(result.message))
                    }

                    DataState.Loading -> Unit
                    is DataState.Success -> {
                        sendEvent(LoginReducer.Event.UpdateLoading(false))
                        sendEffect(LoginReducer.Effect.NavigateToHome)
                    }
                }
            }
        }
    }
}