package ru.orlovegor.weather.data.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import ru.orlovegor.weather.data.local.contracs.CityContract
import ru.orlovegor.weather.data.local.contracs.WeatherPerHourContract
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.local.entity.LocalWeatherPerHour

data class CityWithWeatherPerHour(
    @Embedded
    val city: LocalCity,
    @Relation(
        parentColumn = CityContract.Columns.ID,
        entityColumn = WeatherPerHourContract.Columns.CITY_ID,
    )
    val weatherPerHour: List<LocalWeatherPerHour>
)
