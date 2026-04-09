package com.darek.crosscountry.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {
    @GET("v3.1/all")
    suspend fun getCountries(
        @Query("fields") fields: String = "name,capital,flag"
    ): List<CountryResponse>
}

