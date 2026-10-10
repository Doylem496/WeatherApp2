
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
                    Text("Wild Weather")
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
                            imageVector = Icons.Default.CalendarViewDay,
                            contentDescription = "WeatherToday",
                            modifier = Modifier.size(35.dp)

                        )
                    },
                    label = { Text("Current",
                        modifier = Modifier.size(25.dp)
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
                            imageVector = Icons.Default.CalendarViewWeek,
                            contentDescription = "WeeklyForecast",
                            modifier = Modifier.size(35.dp)
                        )
                    },
                    label = { Text("This Week",
                        modifier = Modifier.size(25.dp)
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
                    NavHost(
                        navController = navController,
                        startDestination = "Current",
                        modifier = Modifier.padding(innerPadding)

                    ){
                        composable(route = "Current"){
                             DisplayWeatherToday(todayWeather)
                        }
                        composable(route = "WeeklyForecast"){
                             displayWeeklyForecast(ForecastThisWeek)
                    }

                }
            }
}










