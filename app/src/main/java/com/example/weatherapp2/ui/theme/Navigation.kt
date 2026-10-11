
package com.example.weatherapp2.ui.theme
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.navigation.compose.NavHost
import com.example.weatherapp2.DisplayWeatherToday
// import com.example.weatherapp.displayWeeklyForecast
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp2.ForecastThisWeek
import com.example.weatherapp2.todayWeather
import com.example.weatherapp2.R
import com.example.weatherapp2.displayWeeklyForecast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
// top and bottom nav bars
@Composable
fun Navigation() {

    // nav controllers help keep track of which item is clicked and handle requests between them
    // here today's weather screen is the default
    // res/values/strings.xml contains app name which you can change
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) } // default set to 0

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Wild Weather - Halifax, Nova Scotia ")
                }
            )
        }, // end top bar

        // bottom seacrh bar
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                windowInsets = NavigationBarDefaults.windowInsets
            )
            {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.WbSunny,
                            contentDescription = "WeatherToday",
                            modifier = Modifier.size(45.dp)

                        )
                    },
                    label = { Text("Current",
                        fontSize = (30.sp)
                        //modifier = Modifier.size(50.dp)
                    ) },
                    selected = selectedIndex == 0,
                    // onClick defines behavior and allows the index to change when clicked
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(route = "Current") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CalendarViewMonth,
                            contentDescription = "WeeklyForecast",
                            modifier = Modifier.size(45.dp)
                        )
                    },
                    label = { Text("This Week",
                        fontSize = (30.sp)
                        //modifier = Modifier.size(55.dp)
                        ) },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(route = "WeeklyForecast") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {

            NavHost(
                navController = navController,
                startDestination = "Current",
                modifier = Modifier.padding(innerPadding)

            ) {
                composable(route = "Current") {
                    DisplayWeatherToday(todayWeather)
                }
                composable(route = "WeeklyForecast") {
                    displayWeeklyForecast(ForecastThisWeek)
                }

            }
        }
    }}










