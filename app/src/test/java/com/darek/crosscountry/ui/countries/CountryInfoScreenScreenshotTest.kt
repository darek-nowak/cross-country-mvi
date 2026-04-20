package com.darek.crosscountry.ui.countries

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.darek.crosscountry.data.models.CountryInfo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountryInfoScreenScreenshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.Light"
    )

    @Test
    fun country_screen_loaded_state() {
        paparazzi.snapshot {
            CountryInfoScreenContent(
                UiCountryInfoState.Success(
                    countryInfo =
                        CountryInfo(
                            name = "Poland",
                            capital = "Warsaw",
                            population = 38000000L,
                            area = 312679.0,
                            continent = "Europe",
                            flag = "🇵🇱",
                            timezone = "UTC+01:00",
                            languages = "Polish",
                            carInfo = "PL, drive on the right"
                        )
                )
            )
        }
    }

    @Test
    fun country_screen_loading_state() {
        paparazzi.snapshot {
            CountryInfoScreenContent(
                UiCountryInfoState.Loading
            )
        }
    }

    @Test
    fun country_screen_error_state() {
        paparazzi.snapshot {
            CountryInfoScreenContent(
                UiCountryInfoState.Error
            )
        }
    }
}