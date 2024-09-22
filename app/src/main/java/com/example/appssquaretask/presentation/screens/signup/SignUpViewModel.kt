package com.example.appssquaretask.presentation.screens.signup

import androidx.lifecycle.viewModelScope
import com.example.appssquaretask.domain.DataState
import com.example.appssquaretask.domain.repository.AuthRepository
import com.example.appssquaretask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<
        SignUpReducer.State,
        SignUpReducer.Event,
        SignUpReducer.Effect
        >(
    initialState = SignUpReducer.initial(),
    reducer = SignUpReducer()
) {
    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch(IO) {
            sendEvent(SignUpReducer.Event.UpdateLoading(true))
            authRepository.signUp(
                name = name,
                email = email,
                password = password
            ).collect {
                when (it) {
                    is DataState.Empty -> Unit
                    is DataState.Error -> {
                        sendEvent(SignUpReducer.Event.UpdateLoading(false))
                        sendEffect(SignUpReducer.Effect.Error(it.message))
                    }

                    DataState.Loading -> Unit
                    is DataState.Success -> {
                        sendEvent(SignUpReducer.Event.UpdateLoading(false))
                        sendEffect(SignUpReducer.Effect.NavigateToLogin)
                    }
                }
            }
        }
    }
}