package com.example.weatherapp2
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp2.ui.theme.Navigation
import com.example.weatherapp2.ui.theme.WeatherApp2Theme
import com.example.weatherapp2.ui.theme.WeatherToday
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.dp
import com.example.weatherapp2.R
import com.example.weatherapp2.ui.theme.WeeklyForecast


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherApp2Theme {
                // Navigation()
                 DisplayWeatherToday(todayWeather)

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
    forecast = "sunny",
    tempInCelciusHigh = 15,
    tempInCelciusLow = 6,
    tempInCelciusCurrent = 9,
    resourceId = R.drawable.cloud
)

@Composable
fun DisplayWeatherToday(weather: WeatherToday){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    )
    {
        Image(painter = painterResource(id = weather.resourceId),
            contentDescription = "weather.forecast"
            )


        Text("${weather.forecast}")

        Text("${weather.tempInCelciusCurrent} C")


        Text("High: ${weather.tempInCelciusHigh} C Low: ${weather.tempInCelciusLow} C")


    }
}





//@Composable
//fun displayWeeklyForecast(){
//    WeeklyForecast(
//        mondayWeather = "cloudy",
//        tuesdayWeather = "rain",
//        wednesdayWeather = "cloudy",
//        thursdayWeather = "windy",
//        fridayWeather = "rain",
//        saturdayWeather = "cloudy",
//        sundayWeather = "sunny"
//    )
//}