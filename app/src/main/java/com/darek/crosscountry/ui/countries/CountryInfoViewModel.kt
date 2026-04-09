package com.darek.crosscountry.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darek.crosscountry.data.CountriesRepository
import com.darek.crosscountry.data.models.CountryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CountryInfoIntent {
    data class ScreenReady(val countryName: String) : CountryInfoIntent()
}

internal sealed class UiCountryInfoState {
    object Loading : UiCountryInfoState()
    data class Success(val countryInfo: CountryInfo) : UiCountryInfoState()
    object Error : UiCountryInfoState()
}
@HiltViewModel
internal class CountryInfoViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiCountryInfoState>(UiCountryInfoState.Loading)
    val uiCountryInfoState = _uiState.asStateFlow()


    private fun fetchCountryInfo(countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { countriesRepository.getCountry(countryName) }
                .onSuccess { _uiState.value = UiCountryInfoState.Success(it) }
                .onFailure {
                    println(it.toString())
                    _uiState.value = UiCountryInfoState.Error
                }
        }
    }

    fun sendIntent(intent: CountryInfoIntent) {
        when (intent) {
            is CountryInfoIntent.ScreenReady -> {
                fetchCountryInfo(intent.countryName)
            }
        }
    }
}