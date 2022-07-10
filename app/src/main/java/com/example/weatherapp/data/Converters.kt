package com.example.weatherapp.data

import com.example.weatherapp.common.Constants.CELSIUS_IN_KELVIN
import com.example.weatherapp.common.Constants.SECONDS_IN_HOUR
import com.example.weatherapp.domain.models.Main
import com.example.weatherapp.domain.models.WeatherModel

class Converters {

    private fun convertKelvinToCCelsius(kelvin: Double): Double {
        return kelvin - CELSIUS_IN_KELVIN
    }

    private fun convertModelMain(main: Main): Main {
        return Main(
            grnd_level = main.grnd_level,
            sea_level = main.sea_level,
            humidity = main.humidity,
            pressure = main.pressure,
            feels_like = convertKelvinToCCelsius(main.feels_like),
            temp = convertKelvinToCCelsius(main.temp),
            temp_max = convertKelvinToCCelsius(main.temp_max),
            temp_min = convertKelvinToCCelsius(main.temp_min)
        )
    }

    private fun convertToNormalTimezone(timeZone: Int): Int {
        return timeZone / SECONDS_IN_HOUR
    }

    fun convertWeatherModel(response: WeatherModel) = WeatherModel(
        base = response.base,
        clouds = response.clouds,
        cod = response.cod,
        coord = response.coord,
        dt = response.dt,
        id = response.id,
        main = convertModelMain(response.main),
        name = response.name,
        sys = response.sys,
        timezone = convertToNormalTimezone(response.timezone),
        visibility = response.visibility,
        weather = response.weather,
        wind = response.wind
    )
}