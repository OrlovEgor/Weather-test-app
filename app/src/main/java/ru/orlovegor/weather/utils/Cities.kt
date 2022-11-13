package ru.orlovegor.weather.utils

import androidx.annotation.StringRes
import ru.orlovegor.weather.R

enum class Cities(
    val id: Long,
    val requestName: String,
    @StringRes
    val displayName: Int
) {
    MOSCOW(100, "Moscow", R.string.Moscow),
    VOLOGDA(101, "Vologda", R.string.Vologda),
    CHEREPOVETS(102, "Cherepovets", R.string.Cherepovets),
    VLADIVOSTOK(103, "Vladivostok", R.string.Vladivostok),
    SALEKHARD(104, "Salekhard", R.string.Salekhard),
    SAMARA(105, "Samara", R.string.Samara),
    RYAZAN(106, "Ryazan", R.string.Ryazan),
    UFA(107, "Ufa", R.string.Ufa),
    TULA(108, "Tula", R.string.Tula),
    ANAPA(109, "Anapa", R.string.Anapa)
}