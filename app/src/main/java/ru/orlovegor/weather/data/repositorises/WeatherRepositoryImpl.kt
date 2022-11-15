package ru.orlovegor.weather.data.repositorises

import kotlinx.coroutines.CoroutineDispatcher
import ru.orlovegor.weather.data.local.dao.CityDao
import ru.orlovegor.weather.data.local.dao.WeatherPerHourDao
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour
import ru.orlovegor.weather.data.remote.WeatherApi
import ru.orlovegor.weather.di.IoDispatcher
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.UIState
import ru.orlovegor.weather.utils.dataAccessStrategy
import ru.orlovegor.weather.utils.mapToLocalWeatherPerHour
import ru.orlovegor.weather.utils.mapToWeatherPerHour
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDao: CityDao,
    private val weatherPerHourDao: WeatherPerHourDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getOperation(cityId: Long, tittleCity: String, currentDate: String): UIState<List<WeatherPerHour>> {
        return  dataAccessStrategy(
            networkCall = {weatherApi.getWeatherByCity(tittleCity).map { it.mapToWeatherPerHour() }},
            saveCallResult = {saveWeathersByCity(it, LocalCity(cityId, tittleCity, currentDate)) },
            databaseQuery = {loadLocalWeathersByCity(cityId,currentDate).map { it.mapToWeatherPerHour() }},
            coroutineDispatcher = dispatcher
        )
    }

    private suspend fun saveWeathersByCity(forecastHour: List<WeatherPerHour>, city: LocalCity) {
            cityDao.deleteCity(city.cityId)
            cityDao.insertCity(city)
            weatherPerHourDao.insertWeatherPerHour(forecastHour.map {
                it.mapToLocalWeatherPerHour(
                    city.cityId,
                    0) })
        }

    private suspend fun loadLocalWeathersByCity(
        cityId: Long,
        currentDate: String
    ): List<LocalWeatherPerHour> {
        val lastDateUpdate = cityDao.getLastUpdateDate(cityId)
        return if (lastDateUpdate == currentDate) {
                weatherPerHourDao.getWeatherPerHourByCity(cityId)
            } else {
                listOf()
            }
        }
}




