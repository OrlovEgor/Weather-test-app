package ru.orlovegor.weather.utils

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val message: String) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}
