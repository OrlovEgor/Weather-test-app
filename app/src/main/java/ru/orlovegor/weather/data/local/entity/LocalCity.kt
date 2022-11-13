package ru.orlovegor.weather.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.orlovegor.weather.data.local.contracs.CityContract

@Entity(tableName = CityContract.TABLE_NAME)
data class LocalCity(
    @PrimaryKey
    @ColumnInfo(name = CityContract.Columns.ID)
    val cityId: Long,
    @ColumnInfo(name = CityContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = CityContract.Columns.LAST_DATE_UPDATE)
    val lastDateUpdate: String
)
