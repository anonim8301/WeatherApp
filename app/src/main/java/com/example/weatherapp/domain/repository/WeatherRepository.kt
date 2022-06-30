package com.example.weatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.domain.models.WeatherModel
import retrofit2.Response

interface WeatherRepository {

    suspend fun getWeatherByCityName(cityName: String): WeatherModel
}