package com.example.weatherapp2
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp2.ui.theme.Navigation
import com.example.weatherapp2.ui.theme.WeatherApp2Theme
import com.example.weatherapp2.ui.theme.WeatherToday
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp2.R
import com.example.weatherapp2.ui.theme.WeeklyForecast


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherApp2Theme {
                 Navigation()
                 // DisplayWeatherToday(todayWeather)

                }
            }
        }
    }


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    // test comment
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

// use a data class for current weather and plug in test data


// one object
// placeholder data
val todayWeather = WeatherToday(
    forecast = "Cloudy",
    tempInCelciusHigh = 15,
    tempInCelciusLow = 6,
    tempInCelciusCurrent = 9,
    resourceId = R.drawable.cloud
)

@Composable
fun DisplayWeatherToday(weather: WeatherToday){


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(100.dp)


    )
    {
        Image(painter = painterResource(id = weather.resourceId),
            contentDescription = "${weather.forecast}"
            )


        Text(
            "${weather.forecast}",
            fontSize = (50.sp)

        )

        Text(
            "${weather.tempInCelciusCurrent} C",
                    fontSize = (25.sp)
        )


        Text(
            "High: ${weather.tempInCelciusHigh} C Low: ${weather.tempInCelciusLow} C",
            fontSize = (25.sp)
            )


    }
}

val ForecastThisWeek = WeeklyForecast(
    mondayWeather = "Cloudy",
    tuesdayWeather = "Rain",
    wednesdayWeather = "Cloudy",
    thursdayWeather = "Windy",
    fridayWeather = "Rain",
    saturdayWeather = "Snow",
    sundayWeather = "Sunny",
    mondayLow = 6,
    tuesdayLow = 8,
    wednesdayLow = 5,
    thursdayLow = 4,
    fridayLow = 9,
    saturdayLow = 10,
    sundayLow = 5,
    mondayHigh = 15,
    tuesdayHigh = 16,
    WednesdayHigh = 12,
    thursdayHigh = 13,
    fridayHigh = 12,
    saturdayHigh = 16,
    sundayHigh = 17
)



@Composable
fun displayWeeklyForecast(weekly: WeeklyForecast){

    val forecasts = listOf(weekly.mondayWeather, weekly.tuesdayWeather, weekly.wednesdayWeather,
        weekly.thursdayWeather, weekly.fridayWeather, weekly.saturdayWeather, weekly.sundayWeather
        )

    val highs = listOf(weekly.mondayHigh, weekly.tuesdayHigh, weekly.WednesdayHigh, weekly.thursdayHigh,
        weekly.fridayHigh, weekly.saturdayHigh, weekly.sundayHigh)

    val lows = listOf(weekly.mondayLow, weekly.tuesdayLow, weekly.wednesdayLow, weekly.thursdayLow, weekly.fridayLow,
        weekly.saturdayLow, weekly.sundayLow)

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(80.dp)


    ) {

        for ((index, forecast) in (forecasts.withIndex())){
           val imageRes = when (forecast){
                "Cloudy" -> R.drawable.cloud
                "Rain" -> R.drawable.raincloud
                "Sunny" -> R.drawable.sun
                "Windy" -> R.drawable.wind
               "Snow" -> R.drawable.snow
                else -> R.drawable.exclaimationmark
           }
            Column{
                Image(painter= painterResource(imageRes),
                    contentDescription = forecast,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)

                    )
                Text(
                    text = forecast,
                    fontSize = (30.sp)
                )
                Text(
                    text = "High: ${highs[index]} Low: ${lows[index]}"
                )

            }


        }


    }


}