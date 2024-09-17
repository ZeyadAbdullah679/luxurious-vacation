package com.example.appssquaretask.domain.repository

import com.example.appssquaretask.data.model.auth.SignInResponse
import com.example.appssquaretask.data.model.auth.SignUpResponse
import com.example.appssquaretask.domain.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(name: String, email: String, password: String): Flow<DataState<SignUpResponse>>

    suspend fun signIn(email: String, password: String): Flow<DataState<SignInResponse>>

}