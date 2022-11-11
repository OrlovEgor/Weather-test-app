package ru.orlovegor.weather.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.FragmentChoiseCityBinding

class FragmentChoiceCity : Fragment(R.layout.fragment_choise_city) {

    private val binding: FragmentChoiseCityBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}