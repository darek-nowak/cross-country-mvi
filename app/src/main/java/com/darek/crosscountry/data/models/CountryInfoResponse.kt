package com.darek.crosscountry.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class CountryInfoResponse(
    val name: CountryName,
    val capital: List<String>,
    val population: Long,
    val area: Double,
    val continents: List<String>,
    val flag: String,
    val timezones: List<String>,
    val languages: Map<String, String>,
    val car: CarInfoResponse
)

@Serializable
internal data class CarInfoResponse(
    val signs: List<String>,
    val side: String
)

internal fun CountryInfoResponse.toCountryInfo() = CountryInfo(
    name = name.common,
    capital = capital.joinToString(),
    population = population,
    area = area,
    continent = continents.joinToString(),
    flag = flag,
    timezone = timezones.first(),
    languages = languages.values.joinToString(),
    carInfo = "${car.signs.joinToString()}, drive on the ${car.side}"
)

internal data class CountryInfo(
    val name: String,
    val capital: String,
    val population: Long,
    val area: Double,
    val continent: String,
    val flag: String,
    val timezone: String,
    val languages: String,
    val carInfo: String
)