package ru.orlovegor.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.orlovegor.weather.data.repositorises.WeatherRepository
import ru.orlovegor.weather.data.repositorises.WeatherRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}