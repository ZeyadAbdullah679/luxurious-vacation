package com.example.appssquaretask.presentation.screens.popularRestaurant

import androidx.compose.runtime.Immutable
import com.example.appssquaretask.data.model.city.Restaurant
import com.example.appssquaretask.data.model.city.Restaurants
import com.example.appssquaretask.presentation.base.Reducer

class PopularRestaurantsReducer :
    Reducer<PopularRestaurantsReducer.State, PopularRestaurantsReducer.Event, PopularRestaurantsReducer.Effect> {

    @Immutable
    sealed class Event : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : Event()
        data class PopularRestaurants(val restaurants: List<Restaurant>) : Event()
        data class RestaurantClicked(val restaurantId: Int) : Event()
    }

    @Immutable
    sealed class Effect : Reducer.ViewEffect {
        data class NavigateToRestaurantDetails(val restaurantId: Int) : Effect()
        data class Error(val message: String) : Effect()
    }

    @Immutable
    data class State(
        val restaurants: List<Restaurant>,
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

            is Event.PopularRestaurants -> {
                previousState.copy(
                    isLoading = false,
                    restaurants = event.restaurants
                ) to null
            }

            is Event.RestaurantClicked -> {
                previousState to Effect.NavigateToRestaurantDetails(event.restaurantId)
            }
        }
    }

    companion object {
        fun initial() = State(
            restaurants = emptyList(),
            isLoading = true
        )
    }
}