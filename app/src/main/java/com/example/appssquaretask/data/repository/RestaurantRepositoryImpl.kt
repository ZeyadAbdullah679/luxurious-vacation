package com.example.appssquaretask.data.repository

import com.example.appssquaretask.data.model.city.Restaurants
import com.example.appssquaretask.data.remote.VacationRetrofitService
import com.example.appssquaretask.domain.DataState
import com.example.appssquaretask.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val apiService: VacationRetrofitService
) : RestaurantRepository {
    override suspend fun getRestaurants(): Flow<DataState<Restaurants>> =
        handleApi { apiService.listRestaurants() }


}
