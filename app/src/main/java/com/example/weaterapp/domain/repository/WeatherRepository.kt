package com.example.weaterapp.domain.repository

import com.example.weaterapp.domain.util.Resource
import com.example.weaterapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat:Double, long:Double):Resource<WeatherInfo>
}