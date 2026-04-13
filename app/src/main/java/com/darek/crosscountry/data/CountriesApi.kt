package com.darek.crosscountry.data

import com.darek.crosscountry.data.models.CountryInfoResponse
import com.darek.crosscountry.data.models.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CountriesApi {
    @GET("v3.1/all")
    suspend fun getCountries(
        @Query("fields") fields: String = "name,capital,flag",
    ): List<CountryResponse>

    // https://restcountries.com/v3.1/name/poland?fields=flag,capital,population,area,continents
    @GET("v3.1/name/{name}")
    suspend fun getCountry(
        @Path("name") name: String,
        @Query("fields") fields: String = "name,capital,population,area,continents,flag,timezones,languages,car",
    ): List<CountryInfoResponse>
}
