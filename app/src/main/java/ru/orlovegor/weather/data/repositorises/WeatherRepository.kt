package ru.orlovegor.weather.data.repositorises

import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.UIState

interface WeatherRepository {

    suspend fun getOperation(
        cityId: Long,
        tittleCity: String,
        currentDate: String
    ): UIState<List<WeatherPerHour>>






}