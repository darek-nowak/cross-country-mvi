package com.darek.crosscountry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.darek.crosscountry.ui.countries.CountriesScreen
import com.darek.crosscountry.ui.countries.CountryInfoScreen
import com.darek.crosscountry.ui.theme.CrossCountryTheme
import dagger.hilt.android.AndroidEntryPoint

private const val COUNTRY_INFO_ROUTE = "countryInfo/{country}"
private const val COUNTRY_INFO_ARGUMENT = "country"
private const val COUNTRY_INFO_TITLE = "Country details"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrossCountryTheme {
                CrossCountryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun CrossCountryApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val onCountryDetailsScreen = currentRoute == COUNTRY_INFO_ROUTE

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (onCountryDetailsScreen) {
                TopAppBar(
                    title = { Text(COUNTRY_INFO_TITLE) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (!onCountryDetailsScreen) {
                NavigationBar {
                    AppDestinations.entries.forEach { destination ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.label
                                )
                            },
                            label = { Text(destination.label) },
                            selected = currentRoute == destination.name,
                            onClick = {
                                navController.navigate(destination.name) {
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
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.HOME.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(AppDestinations.HOME.name) {
                CountriesScreen(
                    onCountryClick = { country ->
                        navController.navigate(COUNTRY_INFO_ROUTE.replace("{$COUNTRY_INFO_ARGUMENT}", country))
                    }
                )
            }
            composable(
                COUNTRY_INFO_ROUTE,
                arguments = listOf(navArgument(COUNTRY_INFO_ARGUMENT) { type = NavType.StringType })
            ) { backStackEntry ->
                val country = backStackEntry.arguments?.getString(COUNTRY_INFO_ARGUMENT) ?: ""
                CountryInfoScreen(country)
            }
            composable(AppDestinations.FAVORITES.name) {
                Screen("Favorites")
            }
            composable(AppDestinations.PROFILE.name) {
                Screen("Profile")
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}

@Composable
fun Screen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello at screen $name!",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    CrossCountryTheme {
        Screen("Android")
    }
}