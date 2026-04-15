package com.darek.crosscountry.ui.countries

import app.cash.turbine.test
import com.darek.crosscountry.data.CountriesRepository
import com.darek.crosscountry.data.models.Country
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.io.IOException

class CountriesViewModelTest {
    private val countriesRepository = mockk<CountriesRepository>()

    private val viewModel = CountriesViewModel(countriesRepository)

    @Test
    fun `when screen ready and fetchCountries successful`() = runTest {
        val countries = listOf(
            Country("Country1", "Flag1"),
            Country("Country2", "Flag2")
        )
        coEvery { countriesRepository.getCountries() } returns countries

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiCountriesState.Loading)

            viewModel.sendIntent(CountriesIntent.ScreenReady)

            assertThat(awaitItem()).isEqualTo(UiCountriesState.Success(countries))
        }

    }

    @Test
    fun `when screen ready and fetchCountries failure`() = runTest {
        coEvery { countriesRepository.getCountries() } throws IOException("Error")

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UiCountriesState.Loading)

            viewModel.sendIntent(CountriesIntent.ScreenReady)

            assertThat(awaitItem()).isEqualTo(UiCountriesState.Error)
        }
    }
}
