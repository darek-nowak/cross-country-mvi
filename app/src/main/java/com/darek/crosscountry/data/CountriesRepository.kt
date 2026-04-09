package com.darek.crosscountry.data

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
}
