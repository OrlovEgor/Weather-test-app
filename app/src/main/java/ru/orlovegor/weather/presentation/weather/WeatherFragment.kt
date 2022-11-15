package ru.orlovegor.weather.presentation.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.FragmentWeatherBinding
import ru.orlovegor.weather.utils.ItemOffsetDecoration
import ru.orlovegor.weather.utils.UIState
import ru.orlovegor.weather.utils.makeToast

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding: FragmentWeatherBinding by viewBinding()
    private val viewModel: WeatherViewModel by viewModels()

    private val weatherAdapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModelState()
    }

    private fun observeViewModelState() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiWeather.collectLatest { state ->
                when (state) {
                    is UIState.Error -> {
                        makeToast(requireContext(), R.string.error)
                    }
                    is UIState.NetworkError -> {
                        if (state.data.isNullOrEmpty()) {
                            makeToast(requireContext(), R.string.network_error)
                        } else {
                            makeToast(requireContext(), R.string.network_error_load)
                            weatherAdapter.submitList(state.data)
                        }
                    }
                    is UIState.Success -> {
                        weatherAdapter.submitList(state.data)
                    }
                }
            }
        }
        /*lifecycleScope.launchWhenStarted {
            viewModel.weather.collectLatest { weatherPerHour ->
                weatherAdapter.submitList(weatherPerHour)
            }
        }*/
        lifecycleScope.launchWhenStarted {
            viewModel.isProgress.collectLatest { isProgress ->
                binding.weatherProgress.isVisible = isProgress
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.toast.collectLatest { messageRes ->
                makeToast(requireContext(), messageRes)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initList() {
        with(binding.listWeather) {
            adapter = weatherAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(ItemOffsetDecoration(requireContext()))
        }
    }
}