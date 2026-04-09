package com.darek.crosscountry.data

import kotlinx.serialization.Serializable


@Serializable
data class CountryResponse(
    val name: CountryName,
    val capital: List<String>?, // Capital is returned as a list of strings
    val flag: String
)

@Serializable
data class CountryName(
    val common: String
)

internal fun CountryResponse.toCountry() = Country(
    name = name.common,
    capital = capital?.firstOrNull() ?: "Unknown",
    flag = flag
)

internal data class Country(
    val name: String,
    val capital: String,
    val flag: String
)