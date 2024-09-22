package com.example.appssquaretask.presentation.screens.cityDetails

import androidx.compose.runtime.Immutable
import com.example.appssquaretask.presentation.base.Reducer

class RestaurantDetailsReducer :
    Reducer<RestaurantDetailsReducer.State, RestaurantDetailsReducer.Event, RestaurantDetailsReducer.Effect> {

    @Immutable
    sealed class Event : Reducer.ViewEvent {
        data class UpdateLoading(val isLoading: Boolean) : Event()
    }

    @Immutable
    sealed class Effect : Reducer.ViewEffect {
        data class Error(val message: String) : Effect()
    }

    @Immutable
    data class State(
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
        }
    }

    companion object{
        fun initial() = State(isLoading = true)
    }
}