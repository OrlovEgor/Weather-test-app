package ru.orlovegor.weather.utils

import androidx.annotation.DrawableRes
import ru.orlovegor.weather.R

enum class IconWeather(
    val code: Int,
    @DrawableRes
    val iconDay: Int,
    @DrawableRes
    val iconNight: Int
) {

    CLEAR(1000, R.drawable.d_113, R.drawable.n_113),
    PARTLY(1003,R.drawable.d_116,R.drawable.n_116 ),
    CLOUDY(1006, R.drawable.d_119,R.drawable.n_119 ),
    OVERCAST(1009, R.drawable.d_122, R.drawable.n_122),
    MIST(1030, R.drawable.d_143, R.drawable.n_143),
    PATCHY_RAIN(1063, R.drawable.d_176,R.drawable.n_176),
    PATCHY_SNOW(1066, R.drawable.d_179, R.drawable.n_179),
    PATCHY_SLEET(1069, R.drawable.d_182, R.drawable.n_182),
    PATCHY_FREEZING(1072, R.drawable.d_185, R.drawable.n_185),
    THUNDERY(1087, R.drawable.d_200, R.drawable.n_200),
    BLOWING(1114,R.drawable.d_227, R.drawable.n_227),
    BLIZZARD(1117, R.drawable.d_230,R.drawable.n_230),
    FOG(1135,R.drawable.d_248,R.drawable.n_248),
    FREEZING_FOG(1147,R.drawable.d_260, R.drawable.n_260),
    PATCHY_LIGHT(1150, R.drawable.d_263, R.drawable.n_263),
    LIGHT_DRIZZLE(1153, R.drawable.d_266,R.drawable.n_266),
    FREEZING(1168,R.drawable.d_281,R.drawable.n_281),
    HEAVY_FREEZING(1171,R.drawable.d_284,R.drawable.n_284),
    PATCHY_LIGHT_RAIN(1180,R.drawable.d_293, R.drawable.d_293),
    LIGHT_RAIN(1183,R.drawable.d_296,R.drawable.n_296),
    MODERATE_RAIN_TIMES(1186,R.drawable.d_299,R.drawable.n_299),
    MODERATE_RAIN(1189,R.drawable.d_302,R.drawable.n_302),
    HEAVY_RAIN_TIMES(1192, R.drawable.d_305,R.drawable.n_305),
    HEAVY_RAIN(1195,R.drawable.d_308,R.drawable.n_308),
    LIGHT_FREEZING_RAIN(1198,R.drawable.d_311,R.drawable.n_311),
    HEAVY_FREEZING_RAIN(1201,R.drawable.d_314,R.drawable.n_314 ),
    LIGHT_SLEET(1204,R.drawable.d_317,R.drawable.n_317),
    HEAVY_SLEET(1207,R.drawable.d_320,R.drawable.n_320),
    PATCHY_LIGHT_SNOW(1210, R.drawable.d_323,R.drawable.n_323),
    LIGHT_SNOW(1213,R.drawable.d_326,R.drawable.n_326),
    PATCHY_MODERATE_SNOW(1216,R.drawable.d_329,R.drawable.n_329),
    MODERATE_SHOW(1219,R.drawable.d_332,R.drawable.n_332),
    PATCHY_HEAVY_SNOW(1222,R.drawable.d_335,R.drawable.n_335),
    HEAVY_SNOW(1225,R.drawable.d_338,R.drawable.n_338),
    ICE_PELLETS(1237,R.drawable.d_350,R.drawable.n_350),
    LIGHT_RAIN_SHOWER(1240,R.drawable.d_353,R.drawable.n_353),
    MODERATE_HEAVY_RAIN(1243,R.drawable.d_356,R.drawable.n_356),
    TORRENTIAL_RAIN(1246,R.drawable.d_359,R.drawable.n_359),
    LIGHT_SLEET_SHOWER(1249,R.drawable.d_326,R.drawable.n_326),
    HEAVY_SLEET_SHOWERS(1252,R.drawable.n_365,R.drawable.n_365),
    LIGHT_SNOW_SHOWER(1255,R.drawable.d_368,R.drawable.n_368),
    MODERATE_HEAVY_SNOW(1258,R.drawable.d_371,R.drawable.n_371),
    LIGHT_SHOWER_PELLETS(1261,R.drawable.d_374,R.drawable.n_374),
    HEAVY_SHOWER_PELLETS(1264,R.drawable.d_377,R.drawable.n_377),
    PATCHY_RAIN_THUNDER(1273,R.drawable.d_386,R.drawable.n_386),
    HEAVY_RAIN_THUNDER(1276,R.drawable.d_389,R.drawable.n_389),
    LIGHT_SNOW_THUNDER(1279,R.drawable.d_392,R.drawable.n_392),
    SNOW_THUNDER(1282,R.drawable.d_395,R.drawable.n_395)

}