package com.example.weaterapp.presentation

import com.example.weaterapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo:WeatherInfo? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)