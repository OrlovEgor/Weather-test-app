package ru.orlovegor.weather.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.orlovegor.weather.data.repositorises.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor (
private val weatherRepository: WeatherRepository
): ViewModel() {

        init {
                Log.d("DATA", "WM - INIT")
                viewModelScope.launch {
                        weatherRepository.fetchWeatherByCity("Moscow")
                }
        }
}