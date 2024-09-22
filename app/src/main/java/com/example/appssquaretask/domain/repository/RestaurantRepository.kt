package com.example.appssquaretask.domain.repository

import com.example.appssquaretask.data.model.city.Restaurants
import com.example.appssquaretask.domain.DataState
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getRestaurants(): Flow<DataState<Restaurants>>
}