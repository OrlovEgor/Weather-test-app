package ru.orlovegor.weather.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.FragmentChoiseCityBinding
import ru.orlovegor.weather.utils.Cities

@AndroidEntryPoint
class FragmentChoiceCity : Fragment(R.layout.fragment_choise_city) {

    private val cityTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            changeButtonVisibility(p0.isNullOrBlank())
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private val binding: FragmentChoiseCityBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cityAutoComplete.addTextChangedListener(cityTextWatcher)
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
            findNavController().navigate(
                FragmentChoiceCityDirections.actionFragmentChoiceCityToFragmentWeather(
                    getQueryRequestString(binding.textInputCity.editText?.text.toString() )
                )
            )
        }
    }

    private fun getQueryRequestString(text: String): String{
        return Cities.values().find { requireContext().getString(it.displayName) == text }?.requestName.orEmpty()
    }

    private fun changeButtonVisibility(isEmpty: Boolean) {
        binding.showWeatherButton.isEnabled = !isEmpty
    }

}