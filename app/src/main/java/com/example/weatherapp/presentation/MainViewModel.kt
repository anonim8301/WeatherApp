package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    val myResponse: MutableLiveData<WeatherModel> = MutableLiveData()
    val convertedSunrise: MutableLiveData<String> = MutableLiveData()
    val convertedSunset: MutableLiveData<String> = MutableLiveData()

    private val TAG = "MainViewModel"

    fun getWeatherByCityName(cityName: String) = viewModelScope.launch {
        val response = repository.getWeatherByCityName(cityName)
        myResponse.value = response
        convertedSunrise.value = convertTimestampToTime(response.sys.sunrise)
        convertedSunset.value = convertTimestampToTime(response.sys.sunset)
    }


    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return "00:00"
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}