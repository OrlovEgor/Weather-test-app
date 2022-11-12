package ru.orlovegor.weather.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json")
    suspend fun getWeatherByCity(
        @Query("q") city: String
    ): Response<Weather>
}