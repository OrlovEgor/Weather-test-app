package ru.orlovegor.weather.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.orlovegor.weather.data.WeatherDatabase
import ru.orlovegor.weather.data.local.dao.CityDao
import ru.orlovegor.weather.data.local.dao.WeatherPerHourDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    private const val DB_NAME = "weather_database"

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCityDao(weatherDatabase: WeatherDatabase): CityDao = weatherDatabase.getCityDao()

    @Provides
    @Singleton
    fun provideWeatherPerHourDao(weatherDatabase: WeatherDatabase): WeatherPerHourDao =
        weatherDatabase.getWeatherPerHourDao()
}