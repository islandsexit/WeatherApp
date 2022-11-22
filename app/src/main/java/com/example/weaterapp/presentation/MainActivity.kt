package com.example.weaterapp.presentation

import android.Manifest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weaterapp.presentation.ui.theme.DarkBlue
import com.example.weaterapp.presentation.ui.theme.DeepBlue


import com.example.weaterapp.presentation.ui.theme.WeaterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))

        setContent {
            WeaterAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            DarkBlue
                        )
                ) {
                    WeatherCard(state = viewModel.state, backgroundColor = DeepBlue)
                    Spacer(modifier = Modifier.height(16.dp))
                    WeatherForecast(state = viewModel.state, modifier = Modifier)
                }
                if(viewModel.state.isLoading){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = DeepBlue)
                    }

                }
                viewModel.state.error?.let {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = it, color = Color.Red)
                    }
                }

            }
        }


    }
}

