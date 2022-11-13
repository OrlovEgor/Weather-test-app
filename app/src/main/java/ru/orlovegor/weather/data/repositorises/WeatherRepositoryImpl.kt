package ru.orlovegor.weather.data.repositorises

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.orlovegor.weather.data.local.dao.CityDao
import ru.orlovegor.weather.data.local.dao.WeatherPerHourDao
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.remote.WeatherApi
import ru.orlovegor.weather.di.IoDispatcher
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.mapToLocalWeatherPerHour
import ru.orlovegor.weather.utils.mapToWeatherPerHour
import ru.orlovegor.weather.utils.safeApiCall
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDao: CityDao,
    private val weatherPerHourDao: WeatherPerHourDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun fetchWeatherByCity(city: String) =
        withContext(dispatcher) {
            safeApiCall { weatherApi.getWeatherByCity(city).map { it.mapToWeatherPerHour() } }
        }

    override suspend fun saveDataStrategy(city: LocalCity, weathersPerHour: List<WeatherPerHour>) {
        try {
            cityDao.deleteCity(city.cityId)
            cityDao.insertCity(city)
            weatherPerHourDao.insertWeatherPerHour(weathersPerHour.map {
                it.mapToLocalWeatherPerHour(
                    city.cityId,
                    0
                )
            })
        } catch (t: Throwable) {
            Log.d("DATABASE", "error saveDataStrategy = ${t.message}")
        }
    }

    override suspend fun loadDataStrategy(
        cityId: Long,
        currentDate: String
    ): List<WeatherPerHour> {
        return try {
            val lastDateUpdate = cityDao.getLastUpdateDate(cityId)
            return if (lastDateUpdate == currentDate) {
                weatherPerHourDao.getWeatherPerHourByCity(cityId).map { it.mapToWeatherPerHour() }
            } else {
                listOf()
            }
        } catch (t: Throwable) {
            Log.d("DATABASE", "error loadDataStrategy = ${t.message}")
            listOf()
        }
    }

}

