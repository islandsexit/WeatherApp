package com.example.weaterapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weaterapp.domain.location.LocationTracker
import com.example.weaterapp.domain.repository.WeatherRepository
import com.example.weaterapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let{ location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)){
                    is Resource.Success ->{
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error ->{
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            }?: run{
                state = state.copy(
                    isLoading = false,
                    weatherInfo = null,
                    error = "Не могу получить оступ к местоположению :("
                )
                Log.e("WeatherViewModel", "loadWeatherInfo:error ", )
            }
        }
    }

}