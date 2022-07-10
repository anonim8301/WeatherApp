package com.example.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadData.setOnClickListener {
            viewModel.getWeatherByCityName("moscow")
            viewModel.myResponse.observe(this) {
                with(binding) {
                    tvNameOfTheCity.text = "City: ${it.name}"
                    tvCurrentTemp.text = "Current temp: ${it.main.temp.toInt()}°"
                    tvHighestTemp.text = "Highest temp: ${it.main.temp_max.toInt()}°"
                    tvLowestTemp.text = "Lowest temp: ${it.main.temp_min.toInt()}°"
                    tvHumidity.text = "Humidity: ${it.main.humidity}%"
                    tvWindSpeed.text = "Wind speed: ${it.wind.speed} km/h"
                    tvCloudiness.text = "Cloudiness: ${it.clouds.all}%"
                }
                Log.d(TAG, " AceCard : $it")
            }
            viewModel.convertedSunrise.observe(this) {
                binding.tvSunriseTime.text = "Sunrise: $it"
            }
            viewModel.convertedSunset.observe(this) {
                binding.tvSunsetTime.text = "Sunset: $it"
            }
        }
    }
}