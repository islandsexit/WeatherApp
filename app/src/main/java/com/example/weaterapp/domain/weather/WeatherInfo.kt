package com.example.weaterapp.domain.weather

import com.example.weaterapp.data.remote.WeatherDataDto


data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
