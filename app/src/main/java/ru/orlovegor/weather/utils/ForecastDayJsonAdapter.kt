package ru.orlovegor.weather.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.data.remote.models.Weather

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedForecastHourList

class ForecastDayJsonAdapter {

    @WrappedForecastHourList
    @FromJson
    fun fromJson(weather: Weather): List<ForecastHour> {
        return weather.forecast.forecastDay.first().hour
    }

    @ToJson
    fun toJson(@WrappedForecastHourList value: List<ForecastHour>): Weather {
        throw UnsupportedOperationException()
    }

}