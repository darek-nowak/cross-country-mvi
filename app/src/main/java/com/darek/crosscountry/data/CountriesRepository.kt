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
    suspend fun getCountries(): List<Country> {
        return countriesApi.getCountries()
            .map { it.toCountry() }
    }

    suspend fun getCountry(countryName: String): CountryInfo =
        countriesApi.getCountry(name = countryName)
            .first()
            .toCountryInfo()
}
