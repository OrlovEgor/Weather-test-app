package ru.orlovegor.weather.presentation.models

data class WeatherPerHour(
    val time: String,
    val temperature: String,
    val isDay: Boolean,
    val iconCode: Int
)
