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
import ru.orlovegor.weather.presentation.models.WeatherPerHour
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
                _city.onEach { tittleCity ->
                        _isProgress.value = true

                        when (val result =
                                weatherRepository.getOperation(cityId,tittleCity,getCurrentDate())) {

                                is UIState.Error -> {
                                        _toast.emit(R.string.error)
                                }
                                is UIState.NetworkError -> {
                                        if (result.data.isNullOrEmpty()) {
                                                _toast.emit(R.string.network_error)
                                        } else {
                                                _toast.emit(R.string.network_error_load)
                                                _weather.value = result.data
                                        }
                                }
                                is UIState.Success -> {
                                        _weather.value = result.data
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