package com.darek.crosscountry.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute


// Type-safe route objects for all top-level destinations
@Serializable data object Home
@Serializable data object Favorites
@Serializable data object Profile
@Serializable
data class CountryInfo(val countryName: String)

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox);

    // Navigates to the type-safe route corresponding to this bottom-nav destination
    fun navigate(navController: NavController, builder: NavOptionsBuilder.() -> Unit) {
        when (this) {
            HOME -> navController.navigate(Home, builder)
            FAVORITES -> navController.navigate(Favorites, builder)
            PROFILE -> navController.navigate(Profile, builder)
        }
    }

    // Returns true when this destination matches the current back-stack entry
    fun isSelected(destination: NavDestination?): Boolean = when (this) {
        HOME -> destination?.hasRoute<Home>() == true
        FAVORITES -> destination?.hasRoute<Favorites>() == true
        PROFILE -> destination?.hasRoute<Profile>() == true
    }
}

