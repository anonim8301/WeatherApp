package com.example.weatherapp.data.repository

import com.example.weatherapp.data.Converters
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {
    private val converter = Converters()

    override suspend fun getWeatherByCityName(cityName: String): WeatherModel {
        val response = api.getWeatherByCityName(cityName)
        return converter.convertWeatherModel(response)
    }

}