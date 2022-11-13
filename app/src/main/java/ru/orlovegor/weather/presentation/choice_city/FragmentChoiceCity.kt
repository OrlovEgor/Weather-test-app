package ru.orlovegor.weather.presentation.choice_city

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.FragmentChoiseCityBinding
import ru.orlovegor.weather.utils.Cities

@AndroidEntryPoint
class FragmentChoiceCity : Fragment(R.layout.fragment_choise_city) {

    private val binding: FragmentChoiseCityBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cityAutoComplete.doAfterTextChanged { text -> changeButtonVisibility(text.isNullOrBlank()) }
        navigate()
    }

    override fun onResume() {
        super.onResume()
        initDropDownMenu()
    }

    private fun initDropDownMenu() {
        val itemCities = Cities.values().map {
            requireContext().getString(it.displayName)
        }
        val citiesAdapter =
            ArrayAdapter(requireContext(), R.layout.list_item_dropdown_menu, itemCities)
        (binding.textInputCity.editText as? AutoCompleteTextView)?.setAdapter(citiesAdapter)
    }

    private fun navigate() {
        binding.showWeatherButton.setOnClickListener {
            val city = getCity(binding.textInputCity.editText?.text.toString())
            findNavController().navigate(
                FragmentChoiceCityDirections.actionFragmentChoiceCityToFragmentWeather(
                    city?.requestName.orEmpty(),
                    city?.id ?: 0
                )
            )
        }
    }

    private fun getCity(text: String) =
         Cities.values().find { requireContext().getString(it.displayName) == text }

    private fun changeButtonVisibility(isEmpty: Boolean) {
        binding.showWeatherButton.isEnabled = !isEmpty
    }

}