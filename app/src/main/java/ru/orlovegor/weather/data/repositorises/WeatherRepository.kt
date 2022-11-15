package ru.orlovegor.weather.data.repositorises

import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.ResultWrapper
import ru.orlovegor.weather.utils.UIState

interface WeatherRepository {

   suspend fun fetchWeatherByCity(city: String): ResultWrapper<List<WeatherPerHour>>

   suspend fun saveDataStrategy(city: LocalCity, weathersPerHour: List<WeatherPerHour>)

   suspend fun loadDataStrategy(cityId: Long, currentDate: String): List<WeatherPerHour>

    suspend fun loadDataStrategy2(
      cityId: Long,
      currentDate: String
   ): List<LocalWeatherPerHour>

    suspend fun saveDataStrategy2(forecastHour: List<WeatherPerHour>,city: LocalCity)
    suspend fun getOperation(localCity: LocalCity): UIState<List<WeatherPerHour>>

}