package ru.orlovegor.weather.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour
import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import java.text.SimpleDateFormat
import java.util.*

fun ForecastHour.mapToWeatherPerHour() =
    WeatherPerHour(
        time = this.time.getDateFromString()?.dateToHoursString() ?: "",
        temperature = this.temperature,
        isDay = this.isDay != 0,
        iconCode = this.condition.code
    )

fun ForecastHour.mapToLocalWeatherPerHour(cityId: Long, weatherPerHourId: Long) =
    LocalWeatherPerHour(
        weatherPerHourId = weatherPerHourId,
        parentCityId = cityId,
        time = this.time,
        temperature = this.temperature,
        isDay = this.isDay != 0,
        iconCode = this.condition.code
    )



fun LocalWeatherPerHour.mapToWeatherPerHour() =
    WeatherPerHour(
        time = this.time,
        temperature = this.temperature,
        isDay = this.isDay,
        iconCode = this.iconCode
    )

fun WeatherPerHour.mapToLocalWeatherPerHour(cityId: Long, weatherPerHourId: Long) =
    LocalWeatherPerHour(
        weatherPerHourId = weatherPerHourId,
        parentCityId = cityId,
        time = this.time,
        temperature = this.temperature,
        isDay = this.isDay,
        iconCode = this.iconCode
    )


fun String.getDateFromString(): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.parse(this)
}

fun Date.dateToHoursString(): String {
    val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormatter.format(this)
}

fun Date.dateToDateString(): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormatter.format(this)
}

fun makeToast(context: Context, @StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
