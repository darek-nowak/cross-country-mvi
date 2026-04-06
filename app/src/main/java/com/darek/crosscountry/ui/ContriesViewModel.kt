package com.darek.crosscountry.ui

import androidx.lifecycle.ViewModel
import com.darek.crosscountry.data.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

}
