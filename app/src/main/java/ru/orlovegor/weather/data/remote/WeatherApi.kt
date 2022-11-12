package ru.orlovegor.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.utils.WrappedForecastHourList

interface WeatherApi {
    @GET("/v1/forecast.json")
    @WrappedForecastHourList
    suspend fun getWeatherByCity(
        @Query("q") city: String
    ): List<ForecastHour>
}