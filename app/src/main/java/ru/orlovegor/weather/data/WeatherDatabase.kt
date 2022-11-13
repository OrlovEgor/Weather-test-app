package ru.orlovegor.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.orlovegor.weather.data.WeatherDatabase.Companion.DB_VERSION
import ru.orlovegor.weather.data.local.dao.CityDao
import ru.orlovegor.weather.data.local.dao.WeatherPerHourDao
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour

@Database(
    entities = [
        LocalCity::class,
        LocalWeatherPerHour::class
    ],
    version = DB_VERSION
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getCityDao(): CityDao
    abstract fun getWeatherPerHourDao(): WeatherPerHourDao

    companion object {

        const val DB_VERSION = 1
    }
}