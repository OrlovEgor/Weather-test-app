package ru.orlovegor.weather.data.repositorises

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.orlovegor.weather.data.remote.WeatherApi
import ru.orlovegor.weather.di.IoDispatcher
import ru.orlovegor.weather.utils.mapToHourlyWeather
import ru.orlovegor.weather.utils.safeApiCall
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
   private val weatherApi: WeatherApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun fetchWeatherByCity(city: String) =
        withContext(dispatcher) {
           safeApiCall { weatherApi.getWeatherByCity(city).map { it.mapToHourlyWeather() } }
        }
}

