package ru.orlovegor.weather.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.orlovegor.weather.data.local.contracs.CityContract
import ru.orlovegor.weather.data.local.contracs.WeatherPerHourContract

@Entity(
    tableName = WeatherPerHourContract.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = LocalCity::class,
            parentColumns = [CityContract.Columns.ID],
            childColumns = [WeatherPerHourContract.Columns.CITY_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalWeatherPerHour(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WeatherPerHourContract.Columns.ID)
    val weatherPerHourId: Long,
    @ColumnInfo(name = WeatherPerHourContract.Columns.CITY_ID)
    val parentCityId: Long,
    @ColumnInfo(name = WeatherPerHourContract.Columns.TIME)
    val time: String,
    @ColumnInfo(name = WeatherPerHourContract.Columns.TEMPERATURE)
    val temperature: String,
    @ColumnInfo(name = WeatherPerHourContract.Columns.IS_DAY)
    val isDay: Boolean,
    @ColumnInfo(name = WeatherPerHourContract.Columns.ICON_CODE)
    val iconCode: Int
)
