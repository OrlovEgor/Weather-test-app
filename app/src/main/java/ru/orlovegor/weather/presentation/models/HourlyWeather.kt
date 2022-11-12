package ru.orlovegor.weather.presentation.models

data class HourlyWeather(
    val time: String,
    val temperature: String,
    val isDay: Boolean,
    val iconCode: Int
)
