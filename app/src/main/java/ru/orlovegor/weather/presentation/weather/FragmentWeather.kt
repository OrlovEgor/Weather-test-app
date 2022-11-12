package ru.orlovegor.weather.presentation.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.FragmentWeatherBinding
import ru.orlovegor.weather.utils.Cities

@AndroidEntryPoint
class FragmentWeather : Fragment(R.layout.fragment_weather) {

    private val binding: FragmentWeatherBinding by viewBinding()
    private val viewModel: WeatherViewModel by viewModels()
    private val city: FragmentWeatherArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), city.city, Toast.LENGTH_SHORT).show()

    }
}