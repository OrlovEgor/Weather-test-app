package ru.orlovegor.weather.data.local.contracs

object WeatherPerHourContract {

    const val TABLE_NAME = "weather_per_hour"

    object Columns {
        const val ID = "weather_id"
        const val CITY_ID = "city_id"
        const val TIME = "time"
        const val TEMPERATURE = "temperature"
        const val IS_DAY = "is_day"
        const val ICON_CODE = "icon_code"
    }
}