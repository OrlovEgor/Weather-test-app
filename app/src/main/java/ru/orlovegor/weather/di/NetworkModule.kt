package ru.orlovegor.weather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.orlovegor.weather.data.remote.ConnectionInterceptor
import ru.orlovegor.weather.data.remote.QueryHeader
import ru.orlovegor.weather.data.remote.WeatherApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val API_KEY = "ac0d46c26352497780e62538221211"
    private const val BASE_URL = "https://api.weatherapi.com"
    private const val KEY_HEADER = "key"

    @Provides
    @Singleton
    fun provideOkHttp(
        @LoggingInterceptorOkHttpClient loggingInterceptor: Interceptor,
        @ConnectionInterceptorOkHttpClient connectionInterceptor: Interceptor,
        @ApiKeyInterceptorOkHttpClient apiKeyInterceptor: Interceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(connectionInterceptor)
            .addNetworkInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okhttp: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttp)
        .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @ApiKeyInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideApiKeyInterceptorOkHttpClient(): Interceptor {
        return QueryHeader(KEY_HEADER,API_KEY)
    }

    @ConnectionInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideConnectionInterceptorOkHttpClient(@ApplicationContext context: Context): Interceptor {
        return ConnectionInterceptor(context)
    }

    @LoggingInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideLoggingInterceptorOkHttpClient(
    ): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}