package com.example.weaterapp.data.repository

import android.util.Log
import com.example.weaterapp.data.mappers.toWeatherInfo
import com.example.weaterapp.data.remote.WeatherApi
import com.example.weaterapp.domain.repository.WeatherRepository
import com.example.weaterapp.domain.util.Resource
import com.example.weaterapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
):WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, long).toWeatherInfo()
            )
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"An unknown error")
        }
    }
}