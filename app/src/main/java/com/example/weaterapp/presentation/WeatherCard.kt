package com.example.weaterapp.presentation

import android.util.Log.e
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import com.example.weaterapp.R


@Composable
fun WeatherCard(
    state:WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
){
    state.weatherInfo?.currentWeatherData?.let { data ->


        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Сегодня ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius} °C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherDataDispaly(
                        value = data.pressure.roundToInt(),
                        unit ="мм",
                        icon =ImageVector.vectorResource(id =R.drawable.ic_pressure),
                        textStyle = TextStyle(Color.White)
                    )
                    WeatherDataDispaly(
                        value = data.windSpeed.roundToInt(),
                        unit ="м/cек",
                        icon =ImageVector.vectorResource(id =R.drawable.ic_wind),
                        textStyle = TextStyle(Color.White)
                    )
                    WeatherDataDispaly(
                        value = data.humidity.roundToInt(),
                        unit ="%",
                        icon =ImageVector.vectorResource(id =R.drawable.ic_drop),
                        textStyle = TextStyle(color = Color.White)
                    )
                }

            }
        }
    }
}