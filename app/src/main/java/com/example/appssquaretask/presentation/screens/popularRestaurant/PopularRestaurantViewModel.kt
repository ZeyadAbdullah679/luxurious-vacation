package com.example.appssquaretask.presentation.screens.popularRestaurant

import androidx.lifecycle.viewModelScope
import com.example.appssquaretask.domain.DataState
import com.example.appssquaretask.domain.repository.RestaurantRepository
import com.example.appssquaretask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularRestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) :
    BaseViewModel<PopularRestaurantsReducer.State, PopularRestaurantsReducer.Event, PopularRestaurantsReducer.Effect>(
        initialState = PopularRestaurantsReducer.initial(),
        reducer = PopularRestaurantsReducer()
    ) {

        fun getPopularRestaurants() {
            viewModelScope.launch(IO) {
                sendEvent(PopularRestaurantsReducer.Event.UpdateLoading(true))
                restaurantRepository.getRestaurants().collect{
                    when(it){
                        is DataState.Empty -> Unit
                        is DataState.Error -> {
                            sendEvent(PopularRestaurantsReducer.Event.UpdateLoading(false))
                            sendEffect(PopularRestaurantsReducer.Effect.Error(it.message))
                        }
                        DataState.Loading -> Unit
                        is DataState.Success -> {
                            sendEvent(PopularRestaurantsReducer.Event.UpdateLoading(false))
                            sendEvent(PopularRestaurantsReducer.Event.PopularRestaurants(it.data.data))
                        }
                    }
                }
            }
        }
}