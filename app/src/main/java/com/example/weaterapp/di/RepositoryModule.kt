package com.example.weaterapp.di

import com.example.weaterapp.data.location.DefaultLocationTracker
import com.example.weaterapp.data.repository.WeatherRepositoryImpl
import com.example.weaterapp.domain.location.LocationTracker
import com.example.weaterapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ):WeatherRepository
}