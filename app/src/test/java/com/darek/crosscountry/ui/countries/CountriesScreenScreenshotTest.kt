package com.darek.crosscountry.ui.countries

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.darek.crosscountry.data.models.Country
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountriesScreenScreenshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.Light"
    )

    @Test
    fun countries_screen_loaded_state() {
        paparazzi.snapshot {
            CountriesScreenContent(
                countriesState = UiCountriesState.Success(
                    countries =
                        listOf(
                            Country("Poland", "🇵🇱"),
                            Country("Germany", "🇩🇪"),
                            Country("France", "🇫🇷"),
                        )
                )
            )
        }
    }

    @Test
    fun countries_screen_loading_state() {
        paparazzi.snapshot {
            CountriesScreenContent(
                countriesState = UiCountriesState.Loading
            )
        }
    }

    @Test
    fun countries_screen_error_state() {
        paparazzi.snapshot {
            CountriesScreenContent(
                countriesState = UiCountriesState.Error
            )
        }
    }
}