package ru.orlovegor.weather.utils

import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.presentation.models.HourlyWeather

fun ForecastHour.mapToHourlyWeather() =
    HourlyWeather(
        time = this.time,
        temperature = this.temperature,
        isDay = this.isDay != 0,
        iconCode = this.condition.code
    )
