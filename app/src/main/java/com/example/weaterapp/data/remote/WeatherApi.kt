package com.example.weaterapp.data.remote

import retrofit2.http.Query

interface WeatherApi {

    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    )
}