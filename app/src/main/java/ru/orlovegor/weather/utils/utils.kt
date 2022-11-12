package ru.orlovegor.weather.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import java.text.SimpleDateFormat
import java.util.*

fun ForecastHour.mapToHourlyWeather() =
    WeatherPerHour(
        time = this.time.getDateFromString()?.dateToString() ?: "",
        temperature = this.temperature,
        isDay = this.isDay != 0,
        iconCode = this.condition.code
    )

fun String.getDateFromString(): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.parse(this)
}

fun Date.dateToString(): String {
    val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormatter.format(this)
}

fun makeToast(context: Context, @StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
