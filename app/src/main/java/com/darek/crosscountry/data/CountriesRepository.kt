package com.darek.crosscountry.data

import com.darek.crosscountry.data.models.Country
import com.darek.crosscountry.data.models.CountryInfo
import com.darek.crosscountry.data.models.toCountry
import com.darek.crosscountry.data.models.toCountryInfo
import com.darek.crosscountry.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CountriesRepository @Inject constructor(
        private val countriesApi: CountriesApi,
        private val dispatchersProvider: CoroutineDispatchersProvider
    ) {
        private var cachedCountries: List<Country>? = null

        suspend fun getCountries(): List<Country> {
            return cachedCountries ?: with(dispatchersProvider.io()) {
                countriesApi.getCountries()
                    .map { it.toCountry() }
                    .also { cachedCountries = it }
            }
        }

        suspend fun getCountry(countryName: String): CountryInfo =
            countriesApi.getCountry(name = countryName)
                .first()
                .toCountryInfo()
    }
