package com.example.appssquaretask.data.repository

import com.example.appssquaretask.data.remote.VacationRetrofitService
import com.example.appssquaretask.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val retrofitService: VacationRetrofitService) :
    AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ) = handleApi { retrofitService.signUp(name, email, password) }

    override suspend fun signIn(email: String, password: String) =
        handleApi { retrofitService.signIn(email, password) }
}