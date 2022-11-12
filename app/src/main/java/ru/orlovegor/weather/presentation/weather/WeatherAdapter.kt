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
import ru.orlovegor.weather.presentation.models.HourlyWeather
import ru.orlovegor.weather.utils.IconWeather

class WeatherAdapter :
    ListAdapter<HourlyWeather, WeatherAdapter.HourlyWeatherViewHolder>(HourlyWeatherItemCallback()) {

    private class HourlyWeatherItemCallback : DiffUtil.ItemCallback<HourlyWeather>() {
        override fun areItemsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
            return oldItem == newItem
        }
    }

    class HourlyWeatherViewHolder(
        private val binding: ItemWeatherByTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hourlyWeather: HourlyWeather) {
            val iconWeather = IconWeather.values().find { iconWeather -> iconWeather.code == hourlyWeather.iconCode }
            val currentIcon = if (hourlyWeather.isDay) iconWeather?.iconDay else iconWeather?.iconNight

            with(binding) {
                textTime.text = hourlyWeather.time
                textTemperature.text = hourlyWeather.temperature
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