package ru.orlovegor.weather.utils

sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class NetworkError<out T>(val message: String, val data:T? = null): UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}