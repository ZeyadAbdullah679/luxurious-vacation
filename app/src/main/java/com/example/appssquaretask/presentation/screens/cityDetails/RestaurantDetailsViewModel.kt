package com.example.appssquaretask.presentation.screens.cityDetails

import com.example.appssquaretask.presentation.base.BaseViewModel

class RestaurantDetailsViewModel:
    BaseViewModel<RestaurantDetailsReducer.State, RestaurantDetailsReducer.Event, RestaurantDetailsReducer.Effect>(
        initialState = RestaurantDetailsReducer.initial(),
        reducer = RestaurantDetailsReducer()

    ) {
}