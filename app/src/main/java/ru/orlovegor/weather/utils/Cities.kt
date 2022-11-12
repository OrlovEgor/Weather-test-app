package ru.orlovegor.weather.utils

import androidx.annotation.StringRes
import ru.orlovegor.weather.R

enum class Cities(
    val requestName: String,
    @StringRes
    val displayName: Int
) {
    MOSCOW("Moscow", R.string.Moscow),
    VOLOGDA("Vologda", R.string.Vologda),
    CHEREPOVETS("Cherepovets", R.string.Cherepovets),
    VLADIVOSTOK("Vladivostok", R.string.Vladivostok),
    SALEKHARD("Salekhard", R.string.Salekhard),
    SAMARA("Samara", R.string.Samara),
    RYAZAN("Ryazan", R.string.Ryazan),
    UFA("Ufa", R.string.Ufa),
    TULA("Tula", R.string.Tula),
    ANAPA("Anapa", R.string.Anapa)
}