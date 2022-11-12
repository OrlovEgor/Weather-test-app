package ru.orlovegor.weather.data.repositorises

import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.ResultWrapper

interface WeatherRepository {

    suspend fun fetchWeatherByCity(city: String): ResultWrapper<List<WeatherPerHour>>
}