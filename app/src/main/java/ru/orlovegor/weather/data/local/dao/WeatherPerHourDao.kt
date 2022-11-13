package ru.orlovegor.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.orlovegor.weather.data.local.contracs.CityContract
import ru.orlovegor.weather.data.local.contracs.WeatherPerHourContract
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour

@Dao
interface WeatherPerHourDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherPerHour(weatherPerHour: List<LocalWeatherPerHour>)

    @Query("select * from ${WeatherPerHourContract.TABLE_NAME} where ${CityContract.Columns.ID} = :cityId ")
    suspend fun getWeatherPerHourByCity(cityId: Long): List<LocalWeatherPerHour>


}