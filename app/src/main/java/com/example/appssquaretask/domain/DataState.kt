package com.example.appssquaretask.domain

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val code: Int, val message: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
    data object Empty : DataState<Nothing>()
}