package ru.orlovegor.weather.data.repositorises

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.orlovegor.weather.data.local.dao.CityDao
import ru.orlovegor.weather.data.local.dao.WeatherPerHourDao
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour
import ru.orlovegor.weather.data.remote.WeatherApi
import ru.orlovegor.weather.data.remote.models.ForecastHour
import ru.orlovegor.weather.di.IoDispatcher
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.UIState
import ru.orlovegor.weather.utils.mapToLocalWeatherPerHour
import ru.orlovegor.weather.utils.mapToWeatherPerHour
import ru.orlovegor.weather.utils.safeApiCall
import java.io.IOException
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

    override suspend fun saveDataStrategy2(forecastHour: List<WeatherPerHour>,city: LocalCity) {
            cityDao.deleteCity(city.cityId)
            cityDao.insertCity(city)
            weatherPerHourDao.insertWeatherPerHour(forecastHour.map {
                it.mapToLocalWeatherPerHour(
                    city.cityId,
                    0) })
        }


    override suspend fun loadDataStrategy2(
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


    override suspend fun getOperation(localCity: LocalCity): UIState<List<WeatherPerHour>> {
      return  strategy(
           networkCall = {weatherApi.getWeatherByCity(localCity.title).map { it.mapToWeatherPerHour() }},
           saveCallResult = {  it -> saveDataStrategy2(it, localCity) },
           databaseQuery = {loadDataStrategy2(localCity.cityId,localCity.lastDateUpdate).map { it.mapToWeatherPerHour() }}
        )
    }

    suspend fun<T> strategy (
        networkCall: suspend () -> T,
        saveCallResult: suspend (T) -> Unit,
        databaseQuery: suspend () -> T
    ): UIState<T> {
        return try {
            val loadData = networkCall.invoke()
            saveCallResult.invoke(loadData)
            UIState.Success(loadData)
        }  catch (i: IOException) {
            try {
                val databaseData = databaseQuery.invoke()
                UIState.NetworkError(i.message.toString(),databaseData)
            } catch (t: Throwable){
                UIState.Error(t.message.toString())
            }
        }
    }

    suspend fun<T> strategy2 (
        networkCall: suspend () -> T,
        saveCallResult: suspend () -> Unit,
        databaseQuery: suspend () -> T
    ): Flow<UIState<T>> = flow<UIState<T>> {
        try {
            val loadData = networkCall.invoke()
            saveCallResult.invoke()
           emit(UIState.Success(loadData))
        } catch (i: IOException) {
            try {
                val databaseData = databaseQuery.invoke()
               emit( UIState.NetworkError(i.message.toString(), databaseData))
            } catch (t: Throwable){
                emit(UIState.Error(t.message.toString()))
            }
        }
    }



}

