package com.darek.crosscountry.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
        startDestination = Home,
        modifier = modifier,
    ) {
        composable<Home> {
            CountriesScreen(
                onCountryClick = { countryName ->
                    navController.navigate(route = CountryInfo(countryName))
                },
            )
        }
        composable<CountryInfo> { backStackEntry ->
            val countryInfo: CountryInfo = backStackEntry.toRoute()
            CountryInfoScreen(countryInfo.countryName)
        }

        composable<Favorites> {
            Screen("Favorites")
        }
        composable<Profile> {
            Screen("Profile")
        }
    }
}

@Composable
fun Screen(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello at screen $name!",
        modifier =
            modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    CrossCountryTheme {
        Screen("Android")
    }
}
