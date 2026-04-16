package com.darek.crosscountry.data

import com.darek.crosscountry.data.models.Country
import com.darek.crosscountry.data.models.CountryName
import com.darek.crosscountry.data.models.CountryResponse
import com.darek.crosscountry.util.CoroutineDispatchersExtension
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension


class CountriesRepositoryTest {
    @RegisterExtension
    private val coroutineExtension = CoroutineDispatchersExtension()

    private val countriesApi = mockk<CountriesApi>()

    private val repository = CountriesRepository(
        countriesApi = countriesApi,
        dispatchersProvider = coroutineExtension.testDispatcherProvider
    )

    @Test
    fun `fetch countries from network`() = runTest(coroutineExtension.testDispatcher) {
        coEvery { countriesApi.getCountries() } returns listOf(
            CountryResponse(CountryName("Poland"), "Flag")
        )

        val countries = repository.getCountries()

        assertThat(countries).isEqualTo(listOf(Country("Poland", "Flag")))
    }
}