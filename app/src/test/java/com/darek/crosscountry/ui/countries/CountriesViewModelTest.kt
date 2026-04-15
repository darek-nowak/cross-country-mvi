package com.darek.crosscountry.ui.countries

import com.darek.crosscountry.data.CountriesRepository
import com.darek.crosscountry.data.models.Country
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class CountriesViewModelTest {
    private val countriesRepository = mockk<CountriesRepository>()

    private val viewModel = CountriesViewModel(countriesRepository)

    @Test
    fun `when screen ready fetchCountries success`() = runTest {
        // Given
        val countries = listOf(
            Country("Country1", "Flag1"),
            Country("Country2", "Flag2")
        )
        coEvery { countriesRepository.getCountries() } returns countries

        // When
        viewModel.sendIntent(CountriesIntent.ScreenReady)

        // Then
        val uiState = viewModel.uiState.value
        assertThat(uiState).isEqualTo(UiCountriesState.Success(countries))
    }
}