package com.darek.crosscountry.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    val name: CountryName,
    val flag: String
)

@Serializable
data class CountryName(
    val common: String
)

internal fun CountryResponse.toCountry() = Country(
    name = name.common,
    flag = flag
)

internal data class Country(
    val name: String,
    val flag: String
)