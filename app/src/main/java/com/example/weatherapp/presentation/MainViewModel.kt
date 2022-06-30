package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.models.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    val myResponse: MutableLiveData<WeatherModel> = MutableLiveData()

    private val TAG = "MainViewModel"

    init {
        Log.d(TAG, "MainViewModel")
    }

    fun getWeatherByCityName(cityName: String) = viewModelScope.launch {
        val response = repository.getWeatherByCityName(cityName)
        myResponse.value = response
    }
}