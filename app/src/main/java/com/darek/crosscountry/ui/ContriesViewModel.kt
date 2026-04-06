package com.darek.crosscountry.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darek.crosscountry.data.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            val countries = countriesRepository.getCountries()
            // Handle the list of countries (e.g., update UI state)
            countries.forEach {
                println(it)
            }
        }
    }
}
