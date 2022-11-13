package ru.orlovegor.weather.data.local.dao

import androidx.room.*
import ru.orlovegor.weather.data.local.contracs.CityContract
import ru.orlovegor.weather.data.local.entity.LocalCity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: LocalCity)

    @Query("select ${CityContract.Columns.LAST_DATE_UPDATE} from ${CityContract.TABLE_NAME} where ${CityContract.Columns.ID} = :cityId")
    suspend fun getLastUpdateDate(cityId: Long): String

    @Query("SELECT EXISTS (SELECT 1 FROM ${CityContract.TABLE_NAME} WHERE ${CityContract.Columns.ID} = :cityId)")
    suspend fun existCity(cityId: Long): Boolean

    @Update
    suspend fun updateCityDate(city: LocalCity)

    @Query("delete from ${CityContract.TABLE_NAME} where ${CityContract.Columns.ID} = :cityId")
    suspend fun deleteCity(cityId: Long)

}