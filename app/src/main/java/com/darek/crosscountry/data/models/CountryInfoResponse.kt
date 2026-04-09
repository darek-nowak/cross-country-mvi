package com.darek.crosscountry.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class CountryInfoResponse(
    val name: CountryName,
    val capital: List<String>,
    val population: Long,
    val area: Double,
    val continents: List<String>,
    val flag: String
)

internal fun CountryInfoResponse.toCountryInfo() = CountryInfo(
    name = name.common,
    capital = capital.joinToString(),
    population = population,
    area = area,
    continent = continents.joinToString(),
    flag = flag
)

internal data class CountryInfo(
    val name: String,
    val capital: String,
    val population: Long,
    val area: Double,
    val continent: String,
    val flag: String
)