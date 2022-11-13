package ru.orlovegor.weather.data.local.contracs

object CityContract {

    const val TABLE_NAME = "city"

    object Columns {
        const val ID = "city_id"
        const val TITLE = "title"
        const val LAST_DATE_UPDATE = "last_date_update"
    }
}