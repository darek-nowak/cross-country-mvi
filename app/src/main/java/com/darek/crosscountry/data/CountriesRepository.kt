package com.darek.crosscountry.data

import com.darek.crosscountry.data.models.Country
import com.darek.crosscountry.data.models.CountryInfo
import com.darek.crosscountry.data.models.toCountry
import com.darek.crosscountry.data.models.toCountryInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CountriesRepository @Inject constructor(
    private val countriesApi: CountriesApi
) {
    private var cachedCountries: List<Country>? = null

    suspend fun getCountries(): List<Country> {
        return cachedCountries ?: countriesApi.getCountries()
            .map { it.toCountry() }
            .also { cachedCountries = it }
    }

    suspend fun getCountry(countryName: String): CountryInfo =
        countriesApi.getCountry(name = countryName)
            .first()
            .toCountryInfo()
}
