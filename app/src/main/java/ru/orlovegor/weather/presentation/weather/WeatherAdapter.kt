package ru.orlovegor.weather.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.orlovegor.weather.R
import ru.orlovegor.weather.databinding.ItemWeatherByTimeBinding
import ru.orlovegor.weather.presentation.models.WeatherPerHour
import ru.orlovegor.weather.utils.IconWeather

class WeatherAdapter :
    ListAdapter<WeatherPerHour, WeatherAdapter.HourlyWeatherViewHolder>(HourlyWeatherItemCallback()) {

    private class HourlyWeatherItemCallback : DiffUtil.ItemCallback<WeatherPerHour>() {
        override fun areItemsTheSame(oldItem: WeatherPerHour, newItem: WeatherPerHour): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: WeatherPerHour, newItem: WeatherPerHour): Boolean {
            return oldItem == newItem
        }
    }

    class HourlyWeatherViewHolder(
        private val binding: ItemWeatherByTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherPerHour: WeatherPerHour) {
            val iconWeather = IconWeather.values().find { iconWeather -> iconWeather.code == weatherPerHour.iconCode }
            val currentIcon = if (weatherPerHour.isDay) iconWeather?.iconDay else iconWeather?.iconNight

            with(binding) {
                textTime.text = weatherPerHour.time
                textTemperature.text = weatherPerHour.temperature
                Glide.with(itemView)
                    .load(currentIcon)
                    .error(R.drawable.ic_error_outline_64)
                    .into(iconWeatherImageView)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val binding = ItemWeatherByTimeBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation_alpha)
    }
}