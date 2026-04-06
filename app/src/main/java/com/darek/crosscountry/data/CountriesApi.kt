package com.darek.crosscountry.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {
    @GET("v3.1/all")
    suspend fun getCountries(
        @Query("fields") fields: String = "name,capital"
    ): List<Country>
}

data class Country(
    val name: CountryName,
    val capital: List<String>? // Capital is returned as a list of strings
)

data class CountryName(
    val common: String,
    val official: String
)
