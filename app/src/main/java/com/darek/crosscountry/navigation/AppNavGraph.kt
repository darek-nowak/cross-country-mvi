package com.darek.crosscountry.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.darek.crosscountry.ui.countries.CountriesScreen
import com.darek.crosscountry.ui.countries.CountryInfoScreen
import com.darek.crosscountry.ui.theme.CrossCountryTheme

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME.name,
        modifier = modifier
    ) {
        composable(AppDestinations.HOME.name) {
            CountriesScreen(
                onCountryClick = { country ->
                    navController.navigate(CountryInfoDestination.createRoute(country))
                }
            )
        }
        composable(
            CountryInfoDestination.route,
            arguments = listOf(navArgument(CountryInfoDestination.argument) { type = NavType.StringType })
        ) { backStackEntry ->
            val country = backStackEntry.arguments?.getString(CountryInfoDestination.argument) ?: ""
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

