package com.example.weatherapp.data.remote

import com.example.weatherapp.common.Constants.API_KEY
import com.example.weatherapp.domain.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q=moscow&APPID=
// bee3db30ffaa027892f6395acd72748c

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q")
        cityName: String,
        @Query("appid")
        apiKey: String = API_KEY,
    ): WeatherModel
}