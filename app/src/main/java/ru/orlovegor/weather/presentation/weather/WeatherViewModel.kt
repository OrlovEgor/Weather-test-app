package ru.orlovegor.weather.presentation.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.orlovegor.weather.R
import ru.orlovegor.weather.data.local.entity.LocalCity
import ru.orlovegor.weather.data.repositorises.WeatherRepository
import ru.orlovegor.weather.data.repositorises.WeatherRepositoryImpl
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.ResultWrapper
import ru.orlovegor.weather.utils.UIState
import ru.orlovegor.weather.utils.dateToDateString
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
        private val stateNavArgs: SavedStateHandle,
        private val weatherRepository: WeatherRepository
) : ViewModel() {

        private val cityId: Long
                get() = stateNavArgs.get<Long>(NAV_ARG_KEY_CITY_ID) ?: 0
        private val titleCity: String
        get() = stateNavArgs.get<String>(NAV_ARG_KEY_CITY)?: ""

        private val _city = MutableSharedFlow<String>()
        private val _weather = MutableStateFlow(listOf<WeatherPerHour>())
        private val _uiWeather = weatherRepository.getOperation2(LocalCity(cityId, titleCity , getCurrentDate()))
        private val _isProgress = MutableStateFlow(false)
        private val _toast = MutableSharedFlow<Int>()
        val uiWeather = _uiWeather.shareIn(viewModelScope, SharingStarted.Lazily)
        val weather = _weather.asStateFlow()
        val isProgress = _isProgress.asStateFlow()
        val toast = _toast.asSharedFlow()

        init {
                //load2()
                viewModelScope.launch {
                        stateNavArgs.get<String>(NAV_ARG_KEY_CITY)?.let { _city.emit(it) }
                }
        }

        /*private fun load2(){
                _city.onEach {
                        tittleCity ->
                        _isProgress.value = true
                        _uiWeather.emit(weatherRepository.getOperation( LocalCity(cityId, tittleCity, getCurrentDate())))
                }
                        .onEach { _isProgress.value = false }
                        .launchIn(viewModelScope)
        }*/

        private fun load() {
                _city.onEach { tittleCity ->
                        _isProgress.value = true
                        when (val data = weatherRepository.fetchWeatherByCity(tittleCity)) {
                                is ResultWrapper.Success -> {
                                        _weather.emit(data.value)
                                        weatherRepository.saveDataStrategy(
                                                city = LocalCity(cityId, tittleCity, getCurrentDate()),
                                                weathersPerHour = data.value
                                        )
                                }
                                is ResultWrapper.NetworkError -> {
                                        _weather.value = weatherRepository.loadDataStrategy(
                                                        cityId,
                                                        getCurrentDate()
                                                )
                                        _toast.emit(R.string.network_error)
                                }
                                is ResultWrapper.Error -> {
                                        _toast.emit(R.string.error)
                                }
                        }
                }
                        .onEach { _isProgress.value = false }
                        .launchIn(viewModelScope)
        }

        private fun getCurrentDate() = Calendar.getInstance().time.dateToDateString()

        companion object {
                const val NAV_ARG_KEY_CITY = "city"
                const val NAV_ARG_KEY_CITY_ID = "cityId"
        }
}