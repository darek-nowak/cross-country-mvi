package com.darek.crosscountry.data

import kotlinx.serialization.Serializable


@Serializable
data class Country(
    val name: CountryName,
    val capital: List<String>?, // Capital is returned as a list of strings
    val flag: String
)

@Serializable
data class CountryName(
    val common: String,
    val official: String
)