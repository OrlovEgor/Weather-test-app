package ru.orlovegor.weather.presentation.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.orlovegor.weather.R
import ru.orlovegor.weather.data.repositorises.WeatherRepository
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
        private val stateNavArgs: SavedStateHandle,
        private val weatherRepository: WeatherRepository
) : ViewModel() {

        private val _city = MutableSharedFlow<String>()
        private val _weather = MutableStateFlow(listOf<WeatherPerHour>())
        private val _isProgress = MutableStateFlow(false)
        private val _toast = MutableSharedFlow<Int>()
        val weather = _weather.asStateFlow()
        val isProgress = _isProgress.asStateFlow()
        val toast = _toast.asSharedFlow()

        init {
                load()
                viewModelScope.launch {
                        stateNavArgs.get<String>(NAV_ARG_KEY_CITY)?.let { _city.emit(it) }
                }
        }

        private fun load() {
                _city.onEach {
                        _isProgress.value = true
                                when (val data = weatherRepository.fetchWeatherByCity(it)) {
                                        is ResultWrapper.Success -> {
                                                _weather.emit(data.value)
                                        }
                                        is ResultWrapper.NetworkError -> {
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

        companion object {
                const val NAV_ARG_KEY_CITY = "city"
        }
}