package ru.orlovegor.weather.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "location")
    val location: Location,
    @Json(name = "forecast")
    val forecast: Forecast
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "name")
    val city: String
)

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "forecastday")
    val forecastDay: List<ForecastDay>
)
@JsonClass(generateAdapter = true)
data class ForecastDay(
    @Json(name = "hour")
    val hour : List<ForecastHour>
)
@JsonClass(generateAdapter = true)
data class ForecastHour(
    @Json(name = "time")
    val time: String,
    @Json(name = "temp_c")
    val temperature : String,
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "condition")
    val condition: Condition
)
@JsonClass(generateAdapter = true)
data class Condition(
    @Json(name = "text")
    val text : String,
    @Json(name = "icon")
    val iconUrl: String,
    @Json(name = "code")
    val code: Int
)

